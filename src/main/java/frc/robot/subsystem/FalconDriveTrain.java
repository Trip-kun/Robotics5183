package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.Tuple2;
import frc.robot.subsystem.hardware.SPIGyroscope;
import frc.robot.subsystem.hardware.SparkMotor;
import frc.robot.subsystem.hardware.TalonFXMotor;

public class FalconDriveTrain extends Subsystem {
    public DifferentialDrive drive;
    public TalonFXMotor TalonFXleftMotor;
    public TalonFXMotor TalonFXrightMotor;
    public TalonFXMotor TalonFXrightRearMotor;
    public TalonFXMotor TalonFXleftRearMotor;
    public SparkMotor leftMotor;
    public SparkMotor rightMotor;
    public XboxController xbox;
    public Timer timer = new Timer();
    public SPIGyroscope gyroscope;

    public FalconDriveTrain(TalonFXMotor lm, TalonFXMotor rm, TalonFXMotor lrm, TalonFXMotor rrm, XboxController xbx,
                            SPIGyroscope gyro) {
        // Assigning Values passed into constructor
        TalonFXleftMotor = lm;
        TalonFXrightMotor = rm;
        TalonFXleftRearMotor = lrm;
        TalonFXrightRearMotor = rrm;
        xbox = xbx;
        gyroscope = gyro;
        // Creating a DifferentialDrive used for ArcadeDrive
        drive = new DifferentialDrive(TalonFXleftMotor.getRawMotor(), TalonFXrightMotor.getRawMotor());
        TalonFXleftMotor.getRawMotor().setStatusFramePeriod(21, 20);
        // No need to invert either side. Setting all motors to no inversion.
        TalonFXleftMotor.setInverted(false);
        TalonFXrightMotor.setInverted(true);
        TalonFXleftRearMotor.setInverted(false);
        TalonFXrightRearMotor.setInverted(true);

        // Setting Motor Safety to TRUE. DON'T DISABLE
        TalonFXleftMotor.setSafety(true);
        TalonFXrightMotor.setSafety(true);
        TalonFXleftRearMotor.setSafety(true);
        TalonFXrightRearMotor.setSafety(true);

        // Sets the Rear motors to follow the Front
        lrm.getRawMotor().follow(lm.getRawMotor());
        rrm.getRawMotor().follow(rm.getRawMotor());

        // Ethan probably knows more about this than I do.
        lm.getRawMotor().enableVoltageCompensation(true);
        lrm.getRawMotor().enableVoltageCompensation(true);
        rm.getRawMotor().enableVoltageCompensation(true);
        rrm.getRawMotor().enableVoltageCompensation(true);
        lm.getRawMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lrm.getRawMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rm.getRawMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rrm.getRawMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lm.getRawMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lrm.getRawMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rm.getRawMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rrm.getRawMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lm.getRawMotor().setNeutralMode(NeutralMode.Brake);
        rm.getRawMotor().setNeutralMode(NeutralMode.Brake);
        lrm.getRawMotor().setNeutralMode(NeutralMode.Coast);
        rrm.getRawMotor().setNeutralMode(NeutralMode.Coast);
        lm.getRawMotor().configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);
        lrm.getRawMotor().configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);
        rm.getRawMotor().configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);
        rrm.getRawMotor().configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);
        lm.getRawMotor().configSupplyCurrentLimit(RobotMap.driveCurrentLimit);
        lrm.getRawMotor().configSupplyCurrentLimit(RobotMap.driveCurrentLimit);
        rm.getRawMotor().configSupplyCurrentLimit(RobotMap.driveCurrentLimit);
        rrm.getRawMotor().configSupplyCurrentLimit(RobotMap.driveCurrentLimit);
    }
    public void ArcadeDrive(boolean squared) {
        Tuple2<Double> control = RobotMap.driveTrainControl.getValue();
        drive.arcadeDrive(control.val2, control.val1, squared);
    }


    public void ArcadeDriveAutonomous(boolean squared, double x, double y) {
        // Grabbing Axis Values from Xbox Controller.
        // Grab Value of Left Bumper
        // In all Robot Configs pass values to the main DifferentialDrive for use in
        // arcadeDrive function. No need to use motor SET function.
        drive.arcadeDrive(x*RobotMap.MaxSpeed, y*RobotMap.MaxSpeed, squared);
    }
    @Override
    protected void initDefaultCommand() {

    }

    public void periodic() {

        // In all Robot Configs run left and right motor periodics
        // In STEVE run rear motor periodics
        drive.feedWatchdog();
                TalonFXleftRearMotor.periodic();
                TalonFXrightRearMotor.periodic();
                TalonFXleftMotor.periodic();
                TalonFXrightMotor.periodic();

    }
}