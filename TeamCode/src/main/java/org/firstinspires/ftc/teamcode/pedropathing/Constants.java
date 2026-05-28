package org.firstinspires.ftc.teamcode.pedropathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    /*
    Constants for Pedro Pathing and our Robot
    -- Many of these have to be changed EVERY YEAR

    TODO: update for this year
     */

    public static PathConstraints pathConstraints = new PathConstraints(
            0.99,
            100,
            1,
            1);

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(11.8) //unit = kg
            .forwardZeroPowerAcceleration(-40.443593609141494)
            .lateralZeroPowerAcceleration(-61.34546046201595)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.037, 0, 0.005, 0.025))
           // .headingPIDFCoefficients(new PIDFCoefficients(0.71, 0.02, 0.002, 0.04))
            //.drivePIDFCoefficients(new FilteredPIDFCoefficients(0.3, 0, 0., 0.6, 0.01))
            //.centripetalScaling(0.0005)
            ;
    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("rf_drive")
            .rightRearMotorName("rb_drive")
            .leftRearMotorName("lb_drive")
            .leftFrontMotorName("lf_drive")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(96.63297782357284)
            .yVelocity(76.49313378521776);
    public static PinpointConstants localizerConstants = new PinpointConstants()
            .distanceUnit(DistanceUnit.INCH)
            .forwardPodY(2.5)
            .strafePodX(6.75)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }



}
