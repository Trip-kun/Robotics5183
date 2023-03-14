package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.Tuple2;
import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.hardware.gyro.SingleAxisGyroscope;
import frc.robot.hardware.encoder.Encoder;
import frc.robot.hardware.motor.PhoenixMotor;

public class PhoenixDriveTrain extends Subsystem {
    public DifferentialDrive drive;
    public DifferentialDrive drive2;
    public PhoenixMotor PhoenixLeftMotor;
    public PhoenixMotor PhoenixRightMotor;
    public PhoenixMotor PhoenixRightRearMotor;
    public PhoenixMotor PhoenixLeftRearMotor;
    public XboxController xbox;
    public SingleAxisGyroscope gyroscope;
    public DoubleControlStyle controlStyle;
    public Encoder coder;
    public PhoenixDriveTrain(PhoenixMotor lm, PhoenixMotor rm, PhoenixMotor lrm, PhoenixMotor rrm, XboxController xbx, SingleAxisGyroscope gyro, DoubleControlStyle style, Encoder coder) {

        // Assigning Values passed into constructor
        controlStyle=style;
        PhoenixLeftMotor = lm;
        PhoenixRightMotor = rm;
        PhoenixLeftRearMotor = lrm;
        PhoenixRightRearMotor = rrm;
        xbox = xbx;
        gyroscope = gyro;
        this.coder=coder;
        // Creating a DifferentialDrive used for ArcadeDrive
        drive = new DifferentialDrive(PhoenixLeftMotor, PhoenixRightMotor);
        drive2 = new DifferentialDrive(PhoenixLeftRearMotor,  PhoenixRightRearMotor);
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
        lrm.getRawMasterMotor().setNeutralMode(NeutralMode.Brake);
        rrm.getRawMasterMotor().setNeutralMode(NeutralMode.Brake);
    }
    public void ArcadeDrive(boolean squared) {
        Tuple2<Double> control =controlStyle.getValue();
        drive.arcadeDrive(control.val2, control.val1, squared);
        drive2.arcadeDrive(-control.val2, -control.val1, true);
    }


    public void ArcadeDriveAutonomous(boolean squared, double x, double y) {
        // Grabbing Axis Values from Xbox Controller.
        // Grab Value of Left Bumper
        // In all Robot Configs pass values to the main DifferentialDrive for use in
        // arcadeDrive function. No need to use motor SET function.
        drive.arcadeDrive(x, y, squared);
    }

    protected void initDefaultCommand() {

    }
    public void autonomous(DoubleControlStyle controlStyle) {
        Tuple2<Double> control = controlStyle.getValue();

        drive.arcadeDrive(-control.val2, -control.val1, true);
        drive2.arcadeDrive(control.val2, control.val1, true);
    }
    public void periodic() {

        // In all Robot Configs run Left and Right motor periodics
        // In STEVE run rear motor periodics
                drive.feedWatchdog();
                drive2.feedWatchdog();
                PhoenixLeftRearMotor.periodic();
                PhoenixRightRearMotor.periodic();
                PhoenixLeftMotor.periodic();
                PhoenixRightMotor.periodic();

    }
}
