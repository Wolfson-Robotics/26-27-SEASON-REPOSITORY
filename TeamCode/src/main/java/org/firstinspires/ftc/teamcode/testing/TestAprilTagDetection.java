package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotBase;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.List;

@TeleOp(name = "TestAprilTagDetection", group = "Testing")
public class TestAprilTagDetection extends RobotBase {

    @Override
    public void init() {
        super.init();
        initCamera();
    }

    @Override
    public void loop() {
        if (aTagProc != null) {
            List<AprilTagDetection> detections = aTagProc.getDetections();
            telemetry.addData("# Tags Detected", detections.size());

            for (AprilTagDetection detection : detections) {
                if (detection instanceof AprilTagSingleDetection) {
                    AprilTagSingleDetection singleDetection = (AprilTagSingleDetection) detection;
                    String customName = getTagName(singleDetection.id);
                    
                    if (singleDetection.metadata != null) {
                        telemetry.addLine(String.format("\n==== (ID %d) %s | %s", singleDetection.id, singleDetection.metadata.name, customName));
                        telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", singleDetection.ftcPose.x, singleDetection.ftcPose.y, singleDetection.ftcPose.z));
                        telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", singleDetection.ftcPose.pitch, singleDetection.ftcPose.roll, singleDetection.ftcPose.yaw));
                        telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch/deg)", singleDetection.ftcPose.range, singleDetection.ftcPose.bearing, singleDetection.ftcPose.elevation));
                    } else {
                        telemetry.addLine(String.format("\n==== (ID %d) Unknown | %s", singleDetection.id, customName));
                        if (singleDetection.center != null) {
                            telemetry.addLine(String.format("Center %6.0f %6.0f (pixels)", singleDetection.center.x, singleDetection.center.y));
                        }
                    }
                }
            }
        } else {
            telemetry.addLine("AprilTag Processor not initialized.");
        }
        telemetry.update();
    }

    private String getTagName(int id) {
        if (id == BLUE_TAG) return "BLUE_TAG";
        if (id == RED_TAG) return "RED_TAG";
        if (id == GPP_TAG) return "GPP/PGP_TAG"; // Both are ID 22 in RobotBase
        if (id == PPG_TAG) return "PPG_TAG";
        return "Not a game tag";
    }
}
