package frc.robot.commands;

import frc.robot.Util;
import frc.robot.subsystem.FalconDriveTrain;

import static frc.robot.commands.Direction.CLOCKWISE;
import static frc.robot.commands.Direction.COUNTERCLOCKWISE;

public class TurnDriveTrainThread extends Thread {
    public FalconDriveTrain falconDriveTrain;
    public double newAngle;
    public Direction direction;
    public double MaxSpeed;
    public double t;
    public TurnDriveTrainThread(FalconDriveTrain drivetrain, double newangle, Direction dir, double maxSpeed, double T) {
        falconDriveTrain = drivetrain;
        newAngle = newangle;
        direction = dir;
        MaxSpeed = maxSpeed;
        t = T;
    }

    @Override
    public void run() {
        while (true) {
            double currentAngle = falconDriveTrain.gyroscope.getAngle();
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
                        falconDriveTrain.leftMotor.set(-speed);
                        falconDriveTrain.rightMotor.set(-speed);
                        break;
                    case COUNTERCLOCKWISE:
                        falconDriveTrain.leftMotor.set(speed);
                        falconDriveTrain.rightMotor.set(speed);

                        break;
                }
            }
            if (currentAngle == newAngle && MaxSpeed <= 0.1) {
                falconDriveTrain.leftMotor.set(0);
                falconDriveTrain.rightMotor.set(0);
                break;
            }
        }
    }
}
