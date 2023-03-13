package frc.robot.control.command;

import frc.robot.control.tuple.AutonomousDoubleControl;
import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.subsystem.PhoenixDriveTrain;

import java.time.Duration;
import java.time.Instant;

public class MovePhoenixDriveTrainByTime extends Command {
    @Override
        public void run() {
            if (reverse) {
                dummy.updateValue(-1, 0);
            } else {
                dummy.updateValue(1, 0);
            }
            driveTrain.autonomous(dummy);
        }
    PhoenixDriveTrain driveTrain;
    double time;
    Instant start;
    double speed;
    boolean reverse=false;
    AutonomousDoubleControl dummy = new AutonomousDoubleControl(0, 0);
    DoubleControlStyle style;
    public MovePhoenixDriveTrainByTime(PhoenixDriveTrain dt, double time, double speed, boolean reverse) {
        this.driveTrain=dt;
        this.time=time;
        this.speed=speed;
        this.reverse=reverse;
    }

    @Override
    public void start() {
        style=driveTrain.controlStyle;
        start=Instant.now();
        driveTrain.controlStyle=dummy;
    }

    @Override
    public void clean() {
        driveTrain.controlStyle=style;
        driveTrain.autonomous(style);
    }

    @Override
    public boolean isFinished() {
        return Duration.between(start, Instant.now()).toSeconds()>time;
    }
}
