package frc.robot.control.command;

import frc.robot.control.tuple.AutonomousDoubleControl;
import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.subsystem.PhoenixDriveTrain;

public class TurnPhoenixDriveTrain extends Command {
    enum Direction {
        CLOCKWISE, COUNTERCLOCKWISE
    }
    PhoenixDriveTrain driveTrain;
    double distanceToTurn;
    double startAngle;
    Direction direction;
    double speed;
    DoubleControlStyle style;
    AutonomousDoubleControl dummy = new AutonomousDoubleControl(0, 0);
    public TurnPhoenixDriveTrain(PhoenixDriveTrain dt, double distanceToTurn, double speed, Direction dir) {
        driveTrain=dt;
        this.distanceToTurn=distanceToTurn;
        this.speed=speed;
        direction=dir;
    }
    @Override
    public void run() {
        double x = Math.abs((driveTrain.gyroscope.getAngle()-startAngle)/(distanceToTurn));
        double sqrt = Math.sqrt(1 - (x * x / 2));
        if (direction==Direction.CLOCKWISE) {
            
            dummy.updateValue(sqrt *speed, 0);
        } else {
            dummy.updateValue(-sqrt *speed, 0);
        }
        driveTrain.autonomous(dummy);
    }

    @Override
    public void start() {
        style = driveTrain.controlStyle;
        driveTrain.controlStyle=dummy;
        startAngle=driveTrain.gyroscope.getAngle();
    }

    @Override
    public void clean() {
        driveTrain.controlStyle=style;
        driveTrain.ArcadeDrive(true);
    }

    @Override
    public boolean isFinished() {
        if (direction == Direction.CLOCKWISE) {
            if (driveTrain.gyroscope.getAngle()+distanceToTurn>=startAngle+distanceToTurn) {
                return true;
            } else {
                return false;
            }
        } else {
            if (driveTrain.gyroscope.getAngle()-distanceToTurn<=startAngle-distanceToTurn) {
                return true;
            } else {
                return false;
            }
        }
    }
}
