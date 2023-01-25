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
import frc.robot.subsystem.hardware.PhoenixMotor;

public class PhoenixDriveTrain extends Subsystem {
    public DifferentialDrive drive;
    public PhoenixMotor PhoenixLeftMotor;
    public PhoenixMotor PhoenixRightMotor;
    public PhoenixMotor PhoenixRightRearMotor;
    public PhoenixMotor PhoenixLeftRearMotor;
    public XboxController xbox;
    public Timer timer = new Timer();
    public SPIGyroscope gyroscope;

    public PhoenixDriveTrain(PhoenixMotor lm, PhoenixMotor rm, PhoenixMotor lrm, PhoenixMotor rrm, XboxController xbx,
                             SPIGyroscope gyro) {
        // Assigning Values passed into constructor
        PhoenixLeftMotor = lm;
        PhoenixRightMotor = rm;
        PhoenixLeftRearMotor = lrm;
        PhoenixRightRearMotor = rrm;
        xbox = xbx;
        gyroscope = gyro;
        // Creating a DifferentialDrive used for ArcadeDrive
        drive = new DifferentialDrive(PhoenixLeftMotor, PhoenixRightMotor);
        PhoenixLeftMotor.getRawMasterMotor().setStatusFramePeriod(21, 20);
        // No need to invert either side. Setting all motors to no inversion.
        PhoenixLeftMotor.setInverted(false);
        PhoenixRightMotor.setInverted(true);
        PhoenixLeftRearMotor.setInverted(false);
        PhoenixRightRearMotor.setInverted(true);

        // Setting Motor Safety to TRUE. DON'T DISABLE
        PhoenixLeftMotor.setSafety(true);
        PhoenixRightMotor.setSafety(true);
        PhoenixLeftRearMotor.setSafety(true);
        PhoenixRightRearMotor.setSafety(true);

        // Sets the Rear motors to follow the Front
        lrm.getRawMasterMotor().follow(lm.getRawMasterMotor());
        rrm.getRawMasterMotor().follow(rm.getRawMasterMotor());

        // Ethan probably knows more about this than I do.
        lm.getRawMasterMotor().enableVoltageCompensation(true);
        lrm.getRawMasterMotor().enableVoltageCompensation(true);
        rm.getRawMasterMotor().enableVoltageCompensation(true);
        rrm.getRawMasterMotor().enableVoltageCompensation(true);
        lm.getRawMasterMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lrm.getRawMasterMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rm.getRawMasterMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rrm.getRawMasterMotor().configClosedloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lm.getRawMasterMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lrm.getRawMasterMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rm.getRawMasterMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        rrm.getRawMasterMotor().configOpenloopRamp(RobotMap.DRIVE_SPEED_RAMP);
        lm.getRawMasterMotor().setNeutralMode(NeutralMode.Brake);
        rm.getRawMasterMotor().setNeutralMode(NeutralMode.Brake);
        lrm.getRawMasterMotor().setNeutralMode(NeutralMode.Coast);
        rrm.getRawMasterMotor().setNeutralMode(NeutralMode.Coast);
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

        // In all Robot Configs run Left and Right motor periodics
        // In STEVE run rear motor periodics
        drive.feedWatchdog();
                PhoenixLeftRearMotor.periodic();
                PhoenixRightRearMotor.periodic();
                PhoenixLeftMotor.periodic();
                PhoenixRightMotor.periodic();

    }
}
