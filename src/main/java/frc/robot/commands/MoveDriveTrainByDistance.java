package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
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
    private Timer timer = new Timer();
    // Distance Already Moved
    private boolean isFinished = false;
    public MoveDriveTrainByDistance(double max_speed, double Distance, boolean forwards, DriveTrain drivetrain, Command command) {
        MaxSpeed=max_speed; distance=Distance; Forwards=forwards; endCommand=command;
        requires(drivetrain);
        driveTrain=drivetrain;
    }
    double startTime;
    @Override
    public void start() {
        timer.start(); startTime=timer.getFPGATimestamp();
    }
    double count = 0;
    @Override
    public void execute() {
      //  driveTrain.TalonFXleftMotor.set(MaxSpeed);
       // driveTrain.TalonFXrightMotor.set(MaxSpeed);
        driveTrain.ArcadeDriveAutonomous(true, MaxSpeed, 0);
        count+=0.02;
        double distanceUnits=Math.abs(driveTrain.TalonFXrightMotor.getRawMotor().getSensorCollection().getIntegratedSensorPosition());
        double distanceRotations=distanceUnits/2048;
        double distanceRadians=distanceRotations*2*Math.PI;
        double distanceRadiansAfterGearbox=distanceRadians/ RobotMap.TalonGearbox;
        double distanceNeeded = distanceRadiansAfterGearbox*RobotMap.TalonDiameter/2;
        double distancePrecision= ( (int) (distanceNeeded*10))/10;

        /*
        if (distancePrecision>=distance ) {
            driveTrain.TalonFXleftMotor.set(0.0);
            driveTrain.TalonFXrightMotor.set(0.0);









            isFinished=true;
            scheduler.add(endCommand);
        }

*/


        if (count>=1.5) {
            System.out.println("FINISHED");
            driveTrain.TalonFXleftMotor.set(0.0);
            driveTrain.TalonFXrightMotor.set(0.0);
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
