package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.debug.util.Async;

@Autonomous(name = "BlueAutoFarEnhanced", group = "Auto")
public class BlueAutoFarEnhanced extends AutoBase {

    @Override
    public void start() {
        // 1. Move forward
        moveBot(5, -0.3, 0, 0);
        Async.sleep(1000);

        // 2. Spin up launcher and wait longer for it to reach full velocity
        launcher.applyVelocity(FAR_VELOCITY);
        Async.sleep(1500);

        // 3. Open stopper using 0.0 (since 0.6 closed it) and KEEP IT OPEN while shooting
        stopper.applyPosition(0.0);
        Async.sleep(300);

        // 4. Run feed to shoot and give it plenty of time (4 seconds) to shoot all balls
        runFeed();
        Async.sleep(4000);
        stopFeed();
        stopShoot();

        // 5. Move 1 foot (12 inches) to the right
        moveBot(12, 0, 0, -0.4);
        Async.sleep(1000);

        // 6. Turn 45 degrees counterclockwise
        turnBot(0.3, 45);
        Async.sleep(1000);
    }

    @Override
    public void stop() {
        Async.stopAll();
    }

}
