package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveTrain;

public class MoveDriveTrainByDistance extends Command {
    // Max Speed of Motors.
    private double MaxSpeed;
    // Distance to be moved (IN INCHES UNLESS OTHERWISE SPECIFIED)
    private double Distance;
    // Is Movement Forwards or Backwards? True is forwards
    private boolean Forwards;

    private DriveTrain driveTrain;

    // Distance Already Moved
    private double DistanceMoved=0;
    private boolean isFinished = false;
    public MoveDriveTrainByDistance(double max_speed, double distance, boolean forwards, DriveTrain drivetrain) {
        MaxSpeed=max_speed; Distance=distance; Forwards=forwards;
        requires(drivetrain);
        driveTrain=drivetrain;
    }
    @Override
    public void execute() {
        /*
        Update DistanceMoved through Encoders OR (TIME * RevolutionsPerSecond * DistanceConstant (How many inches covered per 360 degree rotation of the wheels)
        Compare DistanceMoved to Distance. If DistanceMoved == Distance then set isFinished to true

        If not, set the motors to max speed or lower according to the distance left to go.
         */
    }
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
