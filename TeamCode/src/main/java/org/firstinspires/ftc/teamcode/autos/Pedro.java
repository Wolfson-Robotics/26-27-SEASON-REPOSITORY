package org.firstinspires.ftc.teamcode.autos;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedropathing.Constants;

import java.util.Timer;

@Autonomous(name = "DannyLikesJonah")
public class Pedro extends OpMode {

    private Follower follower;
    private Timer pathTimer, opModeTimer;

    private Pose startPose;
    private PathChain mainChain;

    public void buildPaths(Follower follower) {
        startPose = new Pose(56, 8, Math.toRadians(90));

        mainChain = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(56, 8),
                                new Pose(56.652, 46.433)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .addPath(
                        new BezierLine(
                                new Pose(56.652, 46.433),
                                new Pose(94.333, 47.167)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))
                .build();
    }

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        buildPaths(follower);
        follower.setStartingPose(startPose);

    }

    @Override
    public void start() {
        follower.followPath(mainChain);
    }

    @Override
    public void loop() {
        follower.update();

        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }

}
