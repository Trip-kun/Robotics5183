package frc.robot.subsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystem.hardware.SparkMotor;

public class Shooter extends Subsystem {
    SparkMotor leftMotor;
    SparkMotor rightMotor;
    XboxController xbox;
    public Shooter(SparkMotor LeftMotor, SparkMotor RightMotor, XboxController Xbox) {
        leftMotor=LeftMotor;
        rightMotor=RightMotor;
        xbox=Xbox;
    }

    public void Drive() {
        double speed = 0;
        if(xbox.getXButton()==true) {
            speed=-0.80;
        }
        if (xbox.getAButton()==true) {
            speed=0.80;
        }
        if(xbox.getYButton()==true) {
            speed = -0.45;
        }
        if (xbox.getBButton()==true) {
            speed = 0.45;
        }
        leftMotor.set(speed);
        rightMotor.set(-speed);
    }
    public void Shoot() {
        leftMotor.set(-1);
        rightMotor.set(1);
    }
    public void Intake() {
        leftMotor.set(-1);
        rightMotor.set(-1);
    }
    public void Reset() {
        leftMotor.set(0);
        rightMotor.set(0);
    }
    public void periodic() {
        leftMotor.periodic();
        rightMotor.periodic();
    }
    @Override
    protected void initDefaultCommand() {

    }
}
