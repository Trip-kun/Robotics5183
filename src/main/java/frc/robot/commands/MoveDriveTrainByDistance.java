package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.RobotMap;
import frc.robot.subsystem.DriveTrain;

public class MoveDriveTrainByDistance extends Command {
    // Max Speed of Motors.
    private double MaxSpeed;
    // Distance to be moved (IN INCHES UNLESS OTHERWISE SPECIFIED)
    private double distance;
    // Is Movement Forwards or Backwards? True is forwards
    private boolean Forwards;
    private Command endCommand;
    private DriveTrain driveTrain;
    private Scheduler scheduler = Scheduler.getInstance();
    // Distance Already Moved
    private boolean isFinished = false;
    public MoveDriveTrainByDistance(double max_speed, double distance, boolean forwards, DriveTrain drivetrain, Command command) {
        MaxSpeed=max_speed; this.distance=distance; Forwards=forwards; endCommand=command;
        requires(drivetrain);
        driveTrain=drivetrain;
    }
    @Override
    public void execute() {
        driveTrain.leftMotor.set(MaxSpeed);
        driveTrain.rightMotor.set(MaxSpeed);
        double distanceUnits=driveTrain.TalonFXrightMotor.getRawMotor().getSensorCollection().getIntegratedSensorPosition();
        double distanceRotations=distanceUnits/2048;
        double distanceRadians=distanceRotations*2*Math.PI;
        double distanceRadiansAfterGearbox=distanceRadians/ RobotMap.TalonGearbox;
        double distanceNeeded = distanceRadiansAfterGearbox*RobotMap.TalonDiameter/2;
        double distancePrecision= ( (int) (distanceNeeded*10))/10;

        if (distancePrecision>=distance ) {
            driveTrain.leftMotor.set(0);
            driveTrain.rightMotor.set(0);
            isFinished=true;
            scheduler.add(endCommand);
        }
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
