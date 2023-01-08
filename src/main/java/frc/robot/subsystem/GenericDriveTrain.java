package frc.robot.subsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.Tuple2;
import frc.robot.subsystem.hardware.Motor;
import frc.robot.subsystem.hardware.MotorGroup;
import frc.robot.subsystem.hardware.SPIGyroscope;

public class GenericDriveTrain extends Subsystem {
    public DifferentialDrive drive;
    public SPIGyroscope gyro;
    public Motor leftMotor;
    public Motor rightMotor;
    XboxController xbox;
    public GenericDriveTrain(Motor left, Motor right, XboxController xboxController, SPIGyroscope scope) {
        leftMotor=left; rightMotor=right; drive=new DifferentialDrive(left, right);
        rightMotor.setInverted(true);
        rightMotor.setSafety(true);
        leftMotor.setSafety(true);
        xbox=xboxController;
        gyro=scope;
    }
    public GenericDriveTrain(Motor leftRear, Motor leftFront, Motor rightRear, Motor rightFront, XboxController xboxController, SPIGyroscope scope) {
        leftMotor = new MotorGroup(leftFront, leftRear);
        rightMotor = new MotorGroup(rightFront, rightRear);
        drive = new DifferentialDrive(leftMotor, rightMotor);
        rightMotor.setInverted(true);
        rightMotor.setSafety(true);
        leftMotor.setSafety(true);
        xbox=xboxController;
        gyro=scope;
    }

    public void ArcadeDrive(boolean squared) {
        Tuple2<Double> control = RobotMap.driveTrainControl.getValue();
        // In all Robot Configs pass values to the main DifferentialDrive for use in
        // arcadeDrive function. No need to use motor SET function.

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
        leftMotor.periodic();
        rightMotor.periodic();

    }
}
