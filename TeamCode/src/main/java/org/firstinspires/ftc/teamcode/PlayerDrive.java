package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.camera.VisionPortalCamera;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Instructions to connect: adb connect 192.168.43.1:5555
// cd %localappdata%/android/sdk/platform-tools
@TeleOp(name = "PlayerDrive")
public class PlayerDrive extends RobotBase {

    private static final Logger log = LoggerFactory.getLogger(PlayerDrive.class);

    int targetTag = BLUE_TAG;

    @Override
    public void init() {
        super.init();
        initCamera();
    }

    @Override
    public void init_loop() {
        super.init_loop();

        if (gamepad2.dpadLeftWasPressed()) {
            targetTag = BLUE_TAG;
        }
        if (gamepad2.dpadRightWasPressed()) {
            targetTag = RED_TAG;
        }
        commonPrintData();
    }

    @Override
    public void loop() {
        gamepad1Loop();
        gamepad2Loop();
        commonPrintData();
    }

    boolean settingVelocity = false;
    private void gamepad1Loop() {
        //Driving
        driveSystem.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        //Slow down when holding left or right bumper
        if (gamepad1.right_bumper || gamepad1.left_bumper) { driveSystem.powerFactor = 0.2; }
        else { driveSystem.powerFactor = 1.; }
    }

    /*
       --------------------------
        Intake, Launcher, Transfer
       --------------------------
    */
    double curVelTarget = 1350; // Default to min
    boolean stableVelocity = false;
    AprilTagDetection tag = null;
    boolean didFirst = false;
    boolean appliedVelocityDirectly = false;

    private void gamepad2Loop() {
        handleLauncher();
        handleIntakeAndTransfer();
        handleStopper();
    }

    private void handleLauncher() {
        if (gamepad2.xWasPressed()) {
            launcher1.MAX_POWER -= .05;
            launcher2.MAX_POWER -= .05;
        }
        if (gamepad2.bWasPressed()) {
            launcher1.MAX_POWER += .05;
            launcher2.MAX_POWER += .05;
        }
        
        // Updated dpad up/down for fixed velocity values
        if (gamepad2.dpadUpWasPressed()) { curVelTarget = FAR_VELOCITY; }
        if (gamepad2.dpadDownWasPressed()) { curVelTarget = CLOSE_VELOCITY; }
        
        if (gamepad2.dpadLeftWasPressed()) {
            curVelTarget = curVelTarget != CLOSE_VELOCITY ? CLOSE_VELOCITY : FAR_VELOCITY;
        }

        tag = VisionPortalCamera.getTargetTag(aTagProc, targetTag);
        // turret_one.loop(); //NECESSARY FOR TURRET TO WORK

        //Either use velocity or allow drivers to use left stick to use power
        // launcher 1
        if (gamepad2.rightBumperWasReleased()) {
            if (launcher1.targetVelocity == curVelTarget && !didFirst) {
                launcher1.applyVelocity(curVelTarget);
                appliedVelocityDirectly = true;
            } else {
                launcher1.switchVelocity(curVelTarget);
            }
            didFirst = true;
        } else if (launcher1.targetVelocity == 0) {
            launcher1.togglePower(gamepad2.left_trigger > 0.1, launcher1.MAX_POWER);
        }

        // launcher 2
        if (gamepad2.aWasPressed()) {
            if (launcher2.targetVelocity == curVelTarget) {
                launcher2.applyVelocity(curVelTarget);
            } else {
                launcher2.switchVelocity(curVelTarget);
            }
        } else if (launcher2.targetVelocity == 0) {
            launcher2.togglePower(gamepad2.left_trigger > 0.1, launcher2.MAX_POWER);
        }

        stableVelocity = launcher1.reachedVelocity() && launcher1.motor.getVelocity() > 0
                && launcher2.reachedVelocity() && launcher2.motor.getVelocity() > 0;
    }

    private void handleIntakeAndTransfer() {
        if (gamepad2.dpadRightWasPressed()) {
            intake.swapDirection();
            transfer.swapDirection();
            lTransport.swapDirection();
            rTransport.swapDirection();
        }
        boolean ACTIVE_INTAKE = gamepad2.right_trigger > 0.1;
        intake.togglePower(ACTIVE_INTAKE, intake.MAX_POWER);
        transfer.togglePower(ACTIVE_INTAKE, transfer.MAX_POWER);
        lTransport.togglePower(ACTIVE_INTAKE, lTransport.MAX_POWER);
        rTransport.togglePower(ACTIVE_INTAKE, rTransport.MAX_POWER);
    }

    private void handleStopper() {
        stopper.togglePosition(!gamepad2.y);
    }

    /*
         --------------------------
          Telemetry + Debug Data
         --------------------------
      */
    private void commonPrintData() {
        telemetry.addLine("|||--IMPORTANT INFORMATION--|||");
        telemetry.addData("Launcher1 Velocity", launcher1.motor.getVelocity());
        telemetry.addData("Launcher Target Velocity (Supposed)", curVelTarget);
        telemetry.addData("Launcher1 Target Velocity (ACTUAL)", launcher1.targetVelocity);
        telemetry.addData("Launcher Velocity Stable", stableVelocity);
        telemetry.addData("Launcher1 Power", launcher1.motor.getPower());
        telemetry.addData("Launcher2 Velocity", launcher2.motor.getVelocity());
        telemetry.addData("Launcher2 Target Velocity (ACTUAL)", launcher2.targetVelocity);
        telemetry.addData("Launcher2 Power", launcher2.motor.getPower());
        telemetry.addData("April Tag Visible", tag != null);
        telemetry.addLine("|||-------------------------|||");
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addLine("|||----GAMEPAD1 CONTROLS----|||");
        telemetry.addData("Moving (Forward & Strafe)", "Left Stick");
        telemetry.addData("Rotating", "Right Stick");
        telemetry.addData("Slow Down", "Left/Right Bumper");
        telemetry.addLine("|||-------------------------|||");
        telemetry.addLine("|||----GAMEPAD2 CONTROLS----|||");
        telemetry.addData("Launcher 1", "Right Bumper (Toggle)");
        telemetry.addData("Launcher 2", "A Button (Toggle)");
        telemetry.addData("Intake", "Right Trigger (Hold)");
        telemetry.addData("Open Stopper", "Y (Hold)");
        telemetry.addData("Swap Intake Direction", "Dpad Right (Toggle)");
        telemetry.addData("Switch Launcher Velocity", "Dpad Up (Max) / Down (Min)");
        telemetry.addLine("|||-------------------------|||");
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addData("didFirst", didFirst);
        telemetry.addData("appliedVelocityDirectly", appliedVelocityDirectly);
    }
}
