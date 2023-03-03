package frc.robot.subsystem;

//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Tuple2;
import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.hardware.encoder.Encoder;
import frc.robot.hardware.motor.Motor;
import frc.robot.hardware.motor.MotorGroup;
import frc.robot.hardware.SPIGyroscope;

public class GenericDriveTrain extends Subsystem {
    public DifferentialDrive drive;
    public SPIGyroscope gyro;
    public Motor leftMotor;
    public Motor rightMotor;
    DoubleControlStyle controlStyle;
    public Encoder coder;
    public GenericDriveTrain(Motor left, Motor right, SPIGyroscope scope, DoubleControlStyle style, Encoder coder) {
        controlStyle=style;
        leftMotor=left; rightMotor=right; drive=new DifferentialDrive(left, right);
        rightMotor.setInverted(true);
        rightMotor.setSafety(true);
        leftMotor.setSafety(true);
        gyro=scope;
        this.coder=coder;
    }
    public GenericDriveTrain(Motor leftRear, Motor leftFront, Motor rightRear, Motor rightFront, SPIGyroscope scope, DoubleControlStyle style, Encoder coder) {
        controlStyle=style;
        leftMotor = new MotorGroup(leftFront, leftRear);
        rightMotor = new MotorGroup(rightFront, rightRear);
        drive = new DifferentialDrive(leftMotor, rightMotor);
        rightMotor.setInverted(true);
        rightMotor.setSafety(true);
        leftMotor.setSafety(true);
        gyro=scope;
        this.coder=coder;
    }

    public void ArcadeDrive(boolean squared) {
        Tuple2<Double> control = controlStyle.getValue();
        // In all Robot Configs pass values to the main DifferentialDrive for use in
        // arcadeDrive function. No need to use motor SET function.

        drive.arcadeDrive(control.val2, control.val1, squared);
    }
    public void autonomous(DoubleControlStyle controlStyle) {
        Tuple2<Double> control = controlStyle.getValue();

        drive.arcadeDrive(control.val2, control.val1, true);
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

    public void periodic() {

        drive.feedWatchdog();
        leftMotor.periodic();
        rightMotor.periodic();

    }
}
