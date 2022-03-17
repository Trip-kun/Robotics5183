package frc.robot.subsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystem.hardware.SparkMotor;

public class ShooterRotator extends Subsystem {
    SparkMotor motor;
    XboxController xbox;
    public ShooterRotator(SparkMotor sparkMotor, XboxController controller2) {
        motor=sparkMotor; xbox=controller2;
    }
    public void periodic() {
        motor.periodic();
    }
    public void autonomous() {
        motor.set(0.25*xbox.getLeftY());
    }
    @Override
    protected void initDefaultCommand() {

    }
}
