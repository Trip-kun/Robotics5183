package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.subsystem.hardware.RomiInputOutput;
import frc.robot.subsystem.hardware.SPIGyroscope;
import frc.robot.subsystem.hardware.SparkMotor;
import frc.robot.subsystem.hardware.TalonFXMotor;

public class DriveTrain extends Subsystem {
    public DifferentialDrive drive;
    public TalonFXMotor TalonFXleftMotor;
    public TalonFXMotor TalonFXrightMotor;
    public TalonFXMotor TalonFXrightRearMotor;
    public TalonFXMotor TalonFXleftRearMotor;
    public SparkMotor leftMotor;
    public SparkMotor rightMotor;
    public XboxController xbox;
    public RomiInputOutput romiIO;
    public Timer timer = new Timer();
    public SPIGyroscope gyroscope;

    public DriveTrain(TalonFXMotor lm, TalonFXMotor rm, TalonFXMotor lrm, TalonFXMotor rrm, XboxController xbx,
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

    public DriveTrain(SparkMotor l, SparkMotor r, XboxController xbx) {
        // Assigning Values passed into constructor
        leftMotor = l;
        rightMotor = r;
        xbox = xbx;
        // Creating a DifferentialDrive used for ArcadeDrive
        drive = new DifferentialDrive(leftMotor.getRawMotor(), rightMotor.getRawMotor());
        // No need to invert either side. Setting all motors to no inversion.
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);

        // Setting Motor Safety to TRUE. DON'T DISABLE
        leftMotor.setSafety(true);
        rightMotor.setSafety(true);
    }

    public DriveTrain(SparkMotor l, SparkMotor r, XboxController xbx, RomiInputOutput rio) {
        // Assigning Values passed into constructor
        leftMotor = l;
        rightMotor = r;
        xbox = xbx;
        romiIO = rio;
        timer.start();
        // Creating a DifferentialDrive used for ArcadeDrive
        drive = new DifferentialDrive(leftMotor.getRawMotor(), rightMotor.getRawMotor());
        // No need to invert either side. Setting all motors to no inversion.
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);

        // Setting Motor Safety to TRUE. DON'T DISABLE
        leftMotor.setSafety(RobotMap.safety);
        rightMotor.setSafety(RobotMap.safety);
        romiIO.setRedLight(!(RobotMap.safety));
        romiIO.setGreenLight(true);
    }


    public void driveExp() { // cut only allow for values to the hundreth power to be passed to the motors, cut off the rest.
        /** Settings
         xExaggeration: How steep or aggressive the curve is. A higher value bends the curve more, or makes it steeper. For forward/backward speed.
         yExaggeration: See above. For turning speed.
         deadzone: this is a percentage value to avoid any movement when the robot should be stopped. Needed due to the joysticks hardware inaccuracies. Also saves the motors from stalling and saves battery. Compared to input rather than output.
         */

        double x=xbox.getLeftY();

        double z=xbox.getRightX();

        double xExaggeration = 2.0, yExaggeration = 1.0, deadzone = 0.09;

        if(x > deadzone) {
            x = ((java.lang.Math.pow(1+xExaggeration, x)-1)/xExaggeration);
        } else if(x < -deadzone) {
            x = (-((java.lang.Math.pow(1+xExaggeration, -x)-1)/xExaggeration));
        } else {
            x = 0;
        }
        if(z > deadzone) {
            z = (-((java.lang.Math.pow(1+yExaggeration, z)-1))/yExaggeration);
        } else if(z < -deadzone) {
            z = ((java.lang.Math.pow(1+yExaggeration, -z)-1)/yExaggeration);
        } else {
            z = 0;
        }
        drive.arcadeDrive(x, z);
    }
    public void ArcadeDrive(boolean squared) {
        // Grabbing Axis Values from Xbox Controller.
        double y = xbox.getRightX();
        double x = -xbox.getLeftY();
        double multiple =0.60;
        if (xbox.getYButton()) {
            multiple=1.0;
        }
        // Grab Value of Left Bumper
        boolean speed = xbox.getLeftBumper();
        // Halve speed when left bumper is pressed
        if (speed) {
            x = x / 2;
            y = y / 2;
        }
        // In all Robot Configs pass values to the main DifferentialDrive for use in
        // arcadeDrive function. No need to use motor SET function.
        switch (RobotMap.config) {
            case 0:
            case 1:
            case 2:
                drive.arcadeDrive(x*multiple, y*multiple, squared);
                break;
        }
    }


    public void ArcadeDriveAutonomous(boolean squared, double x, double y) {
        // Grabbing Axis Values from Xbox Controller.
        // Grab Value of Left Bumper
        // In all Robot Configs pass values to the main DifferentialDrive for use in
        // arcadeDrive function. No need to use motor SET function.
        switch (RobotMap.config) {
            case 0:
            case 1:
            case 2:
                drive.arcadeDrive(x*0.75, y*0.75, squared);
                break;
        }
    }
    @Override
    protected void initDefaultCommand() {

    }

    public void periodic() {

        // In all Robot Configs run left and right motor periodics
        // In STEVE run rear motor periodics
        switch (RobotMap.config) {
            case 2:
                TalonFXleftRearMotor.periodic();
                TalonFXrightRearMotor.periodic();
                TalonFXleftMotor.periodic();
                TalonFXrightMotor.periodic();
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
                romiIO.setYellowLight((x % 2 == 0));
                break;
        }
    }
}
