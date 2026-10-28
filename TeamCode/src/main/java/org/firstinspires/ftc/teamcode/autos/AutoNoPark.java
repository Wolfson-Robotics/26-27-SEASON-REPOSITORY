package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.debug.util.Async;

@Autonomous(name = "AutoNoPark", group = "Auto")
public class AutoNoPark extends AutoBase {

    @Override
    public void start() {
        // No return to base

        // 2. Spin up launcher and wait longer for it to reach full velocity
        launcher.applyVelocity(FAR_VELOCITY);
        Async.sleep(1500);

        // 3. Open stopper using 0.0 (since 0.6 closed it) and KEEP IT OPEN while shooting
        stopper.applyPosition(0.0);
        Async.sleep(300);

        // 4. Run feed to shoot and give it plenty of time (4 seconds) to shoot all balls
        runFeed();
        Async.sleep(8000);
        stopFeed();
        stopShoot();
    }

    @Override
    public void stop() {Async.stopAll();}

}
