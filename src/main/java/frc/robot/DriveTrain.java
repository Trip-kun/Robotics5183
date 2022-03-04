package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.hardware.Motor;
import frc.robot.hardware.RomiInputOutput;
import frc.robot.hardware.SparkMotor;
import frc.robot.hardware.TalonFXMotor;

public class DriveTrain {
    private DifferentialDrive drive;
    private Motor leftMotor;
    private Motor rightMotor;
    private Motor rightRearMotor;
    private Motor leftRearMotor;
    private XboxController xbox;
    private RomiInputOutput romiIO;
    private Timer timer = new Timer();
    public DriveTrain(TalonFXMotor lm, TalonFXMotor rm, TalonFXMotor lrm, TalonFXMotor rrm, XboxController xbx) {
        // Assigning Values passed into constructor
        leftMotor=lm;
        rightMotor=rm;
        leftRearMotor=lrm;
        rightRearMotor=rrm;
        xbox=xbx;

        // Creating a DifferentialDrive used for ArcadeDrive
        drive = new DifferentialDrive(leftMotor.getRawMotor(), rightMotor.getRawMotor());

        // No need to invert either side. Setting all motors to no inversion.
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);
        leftRearMotor.setInverted(false);
        rightRearMotor.setInverted(false);

        // Setting Motor Safety to TRUE. DON'T DISABLE
        leftMotor.setSafety(true);
        rightMotor.setSafety(true);
        leftRearMotor.setSafety(true);
        rightRearMotor.setSafety(true);

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
    public DriveTrain(Motor l, Motor r, XboxController xbx) {
        // Assigning Values passed into constructor
        leftMotor=l;
        rightMotor=r;
        xbox=xbx;
        // Creating a DifferentialDrive used for ArcadeDrive
        drive=new DifferentialDrive(leftMotor.getRawMotor(), rightMotor.getRawMotor());
        // No need to invert either side. Setting all motors to no inversion.
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);

        // Setting Motor Safety to TRUE. DON'T DISABLE
        leftMotor.setSafety(true);
        rightMotor.setSafety(true);
    }

    public DriveTrain(Motor l, Motor r, XboxController xbx, RomiInputOutput rio) {
        // Assigning Values passed into constructor
        leftMotor=l;
        rightMotor=r;
        xbox=xbx;
        romiIO=rio;
        timer.start();
        // Creating a DifferentialDrive used for ArcadeDrive
        drive=new DifferentialDrive(leftMotor.getRawMotor(), rightMotor.getRawMotor());
        // No need to invert either side. Setting all motors to no inversion.
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);

        // Setting Motor Safety to TRUE. DON'T DISABLE
        leftMotor.setSafety(RobotMap.safety);
        rightMotor.setSafety(RobotMap.safety);
        romiIO.setRedLight(!(RobotMap.safety));
        romiIO.setGreenLight(true);

    }

    public void ArcadeDrive(boolean squared) {
        // Grabbing Axis Values from Xbox Controller.
        double y=xbox.getX(GenericHID.Hand.kRight);
        double x=-xbox.getY(GenericHID.Hand.kLeft);

        // Grab Value of Left Bumper
        boolean speed = xbox.getXButtonPressed();
        // Halve speed when left bumper is pressed
        if (speed) {x=x/2;}

        // In all Robot Configs pass values to the main DifferentialDrive for use in
        // arcadeDrive function. No need to use motor SET function.
        switch (RobotMap.config) {
            case 0:
            case 1:
            case 2:
                drive.arcadeDrive(x, y, squared);
                break;
            }
    }
    public void periodic() {

        // In all Robot Configs run left and right motor periodics
        // In STEVE run rear motor periodics
        switch (RobotMap.config) {
            case 2:
                leftRearMotor.periodic();
                rightRearMotor.periodic();
                leftMotor.periodic();
                rightMotor.periodic();
                break;
            case 1:
                leftMotor.periodic();
                rightMotor.periodic();
                break;
            case 0:
                leftMotor.periodic();
                rightMotor.periodic();
                // Set Romi Yellow Light to turn off and on in a 2 second cycle/
                // Every other second the light is on.
                int x = (int) timer.get();
                romiIO.setYellowLight((x%2 == 0));
                break;
        }
    }
}
