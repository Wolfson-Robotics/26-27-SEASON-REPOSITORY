package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.debug.util.Async;

@Autonomous(name = "AutoReturn", group = "Auto")
public class AutoReturn extends AutoBase {

    @Override
    public void start() {
        // return to base
        // Spin up launcher and wait longer for it to reach full velocity
        launcher.applyVelocity(FAR_VELOCITY);
        Async.sleep(1500);

        // Open stopper using 0.0 (since 0.6 closed it) and KEEP IT OPEN while shooting
        stopper.applyPosition(0.0);
        Async.sleep(300);

        // Run feed to shoot and give it plenty of time (4 seconds) to shoot all balls
        runFeed();
        Async.sleep(8000);
        stopFeed();
        stopShoot();
    // 2 8 2 ft
        // Move 2 feet to the right
        moveBot(24, 0, 0, 0.4);
        Async.sleep(1000);

        // Move 8 ft forward
        moveBot(98, -0.4, 0, 0);
        Async.sleep(1000);

        // 5. Move 2 feet to the right
        moveBot(22, 0, 0, 0.4);
        Async.sleep(1000);
    }

    @Override
    public void stop() {
        Async.stopAll();
    }

}
