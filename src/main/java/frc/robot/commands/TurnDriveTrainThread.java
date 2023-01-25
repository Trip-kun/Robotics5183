package frc.robot.commands;

import frc.robot.Util;
import frc.robot.subsystem.PhoenixDriveTrain;

import static frc.robot.commands.Direction.CLOCKWISE;
import static frc.robot.commands.Direction.COUNTERCLOCKWISE;
@Deprecated
public class TurnDriveTrainThread extends Thread {
    public PhoenixDriveTrain phoenixDriveTrain;
    public double newAngle;
    public Direction direction;
    public double MaxSpeed;
    public double t;
    public TurnDriveTrainThread(PhoenixDriveTrain drivetrain, double newangle, Direction dir, double maxSpeed, double T) {
        phoenixDriveTrain = drivetrain;
        newAngle = newangle;
        direction = dir;
        MaxSpeed = maxSpeed;
        t = T;
    }

    @Override
    public void run() {
        while (true) {
            double currentAngle = phoenixDriveTrain.gyroscope.getAngle();
            double x = Util.normalize_angle_degrees(newAngle - currentAngle);
            double y = x - 360;
            double AbsX = Math.abs(x);
            double AbsY = Math.abs(y);
            if (AbsX < AbsY) {
                if (x < 0) {
                    if (CLOCKWISE != direction) {
                        MaxSpeed -= 0.04;
                    }
                    direction = CLOCKWISE;
                    t = x;
                } else {
                    if (COUNTERCLOCKWISE != direction) {
                        MaxSpeed -= 0.04;
                    }
                    direction = COUNTERCLOCKWISE;
                    t = x;
                }
            } else {
                if (y < 0) {
                    if (CLOCKWISE != direction) {
                        MaxSpeed -= 0.04;
                    }
                    direction = CLOCKWISE;
                    t = y;
                } else {
                    if (COUNTERCLOCKWISE != direction) {
                        MaxSpeed -= 0.04;
                    }
                    direction = COUNTERCLOCKWISE;
                    t = y;
                }
            }
            if (currentAngle != newAngle) {
                double speed = Math.max(0.01, MaxSpeed);
                switch (direction) {
                    case CLOCKWISE:
                       // phoenixDriveTrain.LeftMotor.set(-speed);
                       // phoenixDriveTrain.RightMotor.set(-speed);
                        break;
                    case COUNTERCLOCKWISE:
                       // phoenixDriveTrain.LeftMotor.set(speed);
                       // phoenixDriveTrain.RightMotor.set(speed);

                        break;
                }
            }
            if (currentAngle == newAngle && MaxSpeed <= 0.1) {
                //phoenixDriveTrain.LeftMotor.set(0);
                //phoenixDriveTrain.RightMotor.set(0);
                break;
            }
        }
    }
}
