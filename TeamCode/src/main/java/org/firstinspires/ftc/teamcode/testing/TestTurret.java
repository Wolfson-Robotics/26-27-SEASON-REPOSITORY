package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.components.Turret;
import org.firstinspires.ftc.teamcode.components.camera.VisionPortalCamera;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "TestAimer")
public class TestTurret extends AutoBase {

    @Override
    public void init() {
        super.init();
        initCamera();

        turret_one.curTurretState = Turret.TurretState.GO_TO_GLOBAL_TARGET;
    }

    final double ticsPerRev = 537.7;
    final double gearRatio = 92.0 / 200.0;

    boolean isAiming = false;
    @Override
    public void loop() {
        driveSystem.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        AprilTagDetection tag = VisionPortalCamera.getTargetTag(aTagProc, BLUE_TAG);

        double range = -1;
        double yaw = -1;
        double rangeX = -1;
        double rangeY = -1;

        //TAKEN FROM Aimer.java
        if (tag != null) {
            range = tag.ftcPose.range;
            yaw = tag.ftcPose.bearing;
            rangeX = range * Math.sin(Math.toRadians(turret_one.globalYaw));
            rangeY = range * Math.cos(Math.toRadians(turret_one.globalYaw));
        }
        double distX = pinpoint.getPosX(DistanceUnit.INCH);
        double distY = pinpoint.getPosY(DistanceUnit.INCH);

        turret_one.TARGET_TAG = tag;
        if (gamepad1.xWasPressed()) {
            turret_one.curTurretState = Turret.TurretState.GO_TO_ZERO;
        }
        if (gamepad1.yWasPressed()) {
            turret_one.curTurretState = Turret.TurretState.FOLLOW_TAG;
        }
        //turret.loop();
        turret_one.loop();

        telemetry.addLine("|---POINT AT BLUE TAG---|");
        telemetry.addData("Range", range);
        telemetry.addData("Bearing", yaw);
        telemetry.addData("DistX", distX);
        telemetry.addData("DistY", distY);
        telemetry.addData("robotHeading", turret_one.robotYaw);
        telemetry.addData("rangeX", rangeX);
        telemetry.addData("rangeY", rangeY);
        telemetry.addData("isAiming", isAiming);
        telemetry.addData("encoderPos", turret_one.encoderPos);
        telemetry.addData("localAngle", turret_one.turretYaw);
        telemetry.addData("targetPos", turret_one.targetPos);
        telemetry.addData("targetAngle", turret_one.TARGET_TURRET_YAW);
        telemetry.addData("currentPos", turret_one.motor.getCurrentPosition());
        telemetry.addData("globalAngle", turret_one.globalYaw);
        telemetry.addData("targetGlobalYaw", turret_one.TARGET_GLOBAL_YAW);
        telemetry.update();
    }

}
