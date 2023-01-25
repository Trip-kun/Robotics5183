package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.RobotMap;
import frc.robot.subsystem.GenericDriveTrain;

public class MoveDriveTrainByDistance extends Command {
    // Max Speed of Motors.
    private double MaxSpeed;
    // Distance to be moved (IN INCHES UNLESS OTHERWISE SPECIFIED)
    private double distance;
    // Is Movement Forwards or Backwards? True is forwards
    private boolean Forwards;
    private boolean secondRun;
    private Command endCommand;
    private GenericDriveTrain falconDriveTrain;
    private Scheduler scheduler = Scheduler.getInstance();
    private Timer timer = new Timer();
    // Distance Already Moved
    private boolean isFinished = false;
    public MoveDriveTrainByDistance(double max_speed, double Distance, boolean forwards, GenericDriveTrain drivetrain, Command command) {
        MaxSpeed=max_speed; distance=Distance; Forwards=forwards; endCommand=command;
        requires(drivetrain);
        falconDriveTrain =drivetrain;
    }
    double startTime;
    @Override
    public void start() {
        timer.start(); startTime=timer.getFPGATimestamp();
        secondRun=false;
    }
    double count = 0;
    @Override
    public void execute() {
        falconDriveTrain.leftMotor.set(MaxSpeed);
        falconDriveTrain.rightMotor.set(MaxSpeed);
        falconDriveTrain.ArcadeDriveAutonomous(true, MaxSpeed, 0);
        count+=0.02;
        if (!secondRun) {
            //phoenixDriveTrain.rightMotor.getRawMotor().getSensorCollection().setIntegratedSensorPosition(0, 200);
        }

        //double distanceUnits=Math.abs(phoenixDriveTrain.rightMotor.getRawMotor().getSensorCollection().getIntegratedSensorPosition());
        double distanceUnits=0;
        double distanceRotations=distanceUnits/2048;
        double distanceRadians=distanceRotations*2*Math.PI;
        double distanceRadiansAfterGearbox=distanceRadians/ RobotMap.TalonGearbox;
        double distanceNeeded = distanceRadiansAfterGearbox*RobotMap.TalonDiameter/2;
        double distancePrecision= ( (int) (distanceNeeded*100))/100;


        if (distancePrecision>=distance ) {
            falconDriveTrain.leftMotor.set(0.0);
            falconDriveTrain.rightMotor.set(0.0);









            isFinished=true;
            scheduler.add(endCommand);
        }
        /*
        double offset=8;
        if (count<=offset) {
            driveTrain.leftMotor.set(0.0);
            driveTrain.rightMotor.set(0.0);
        }
        if (count>=2.5+offset) {
            System.out.println("FINISHED");
            driveTrain.leftMotor.set(0.0);
            driveTrain.rightMotor.set(0.0);
            isFinished=true;
            scheduler.add(endCommand);
        }


        /*

        Update DistanceMoved through Encoders OR (TIME * RevolutionsPerSecond * DistanceConstant (How many inches covered per 360 degree rotation of the wheels)
        Compare DistanceMoved to Distance. If DistanceMoved == Distance then set isFinished to true

        If not, set the motors to max speed or lower according to the distance left to go.
         */
        secondRun=true;
   }
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
