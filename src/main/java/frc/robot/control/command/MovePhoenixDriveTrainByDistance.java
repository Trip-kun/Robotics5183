package frc.robot.control.command;

import frc.robot.RobotMap;
import frc.robot.control.tuple.AutonomousDoubleControl;
import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.hardware.encoder.Encoder;
import frc.robot.subsystem.PhoenixDriveTrain;

public class MovePhoenixDriveTrainByDistance extends Command {
    PhoenixDriveTrain driveTrain;
    Encoder coder;
    double distance;
    boolean reverse;
    double speed;
    DoubleControlStyle style;
    AutonomousDoubleControl dummy = new AutonomousDoubleControl(0, 0);
    public MovePhoenixDriveTrainByDistance(PhoenixDriveTrain dt, double dist_inches, double speed, boolean reverse) {
        coder.reset();
        driveTrain=dt;
        distance=dist_inches;
        this.reverse=reverse;
        coder=dt.coder;
        require(dt);
        this.speed=speed;
    }
    public MovePhoenixDriveTrainByDistance(PhoenixDriveTrain dt, double dist_inches, double speed) {
        driveTrain=dt;
        distance=dist_inches;
        this.reverse=false;
        coder=dt.coder;
        require(dt);
        this.speed=speed;
    }
    private double getDistanceTravelled() {
        return (coder.getUnitsRadians())*RobotMap.TalonDiameter/2;
    }
    @Override
    public void run() {
        if (reverse) {
            dummy.updateValue(0, -speed);
        } else {
            dummy.updateValue(0, speed);
        }
        driveTrain.autonomous(dummy);
    }

    @Override
    public void start() {
        style= driveTrain.controlStyle;
        driveTrain.controlStyle = dummy;
    }

    @Override
    public void clean() {
        driveTrain.controlStyle=style;
    }

    @Override
    public boolean isFinished() {
        if (reverse) {
            return getDistanceTravelled() < -distance;
        } else {
            return getDistanceTravelled() > distance;
        }
    }
}
