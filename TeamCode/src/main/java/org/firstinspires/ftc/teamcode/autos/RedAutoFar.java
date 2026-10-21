package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.debug.util.Async;

@Autonomous(name = "RedAutoFar", group = "Auto")
public class RedAutoFar extends AutoBase {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        launcher1.applyVelocity(FAR_VELOCITY);
        moveBot(5, 0.3, 0, 0);
        Async.sleep(250);
        turnBot(0.3,  17.5);
        Async.sleep(250);
        shootBetter(FAR_VELOCITY);
        moveBot(16, 0.4, 0, 0);

        turnBot(0.3, 58.0);
        //Async.sleep(400);
        runFeed();
        moveBot(6, 0, 0, -0.4);
        Async.sleep(300);
        moveBot(45, 0.2, 0, 0);
        //Async.sleep(100);
        launcher1.applyVelocity(FAR_VELOCITY);
        stopFeed();
        moveBot(7, 0.4, 0, 0);
//        stopFeed();
        //Go back
        Async.sleep(200);
        moveBot(50, -0.4, 0, 0);
        Async.sleep(400);
        moveBot(30, 0, 0, 0.4);
        //moveBotDiagonal(30, 50, -0.4, 0.4);
        Async.sleep(250);
        moveBot(7.5, 0, 0, -0.4);
        //turnBot(0.2, -57.3);
        turnBot(0.3, -49.7);
        shootBetter(FAR_VELOCITY);
        moveBot(16, 0.2, 0, 0);
    }


}
