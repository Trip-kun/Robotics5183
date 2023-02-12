package frc.robot.hardware.motor;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class MotorGroup extends Motor {
    public Motor motor1;
    public Motor motor2;
    public MotorGroup(Motor mot1, Motor mot2) {
        motor1=mot1;
        motor2=mot2;
    }

    @Override
    public void set(double speed) {
        motor1.set(speed);
        motor2.set(speed);
    }

    @Override
    public void setVoltage(double outputVolts) {
        motor1.setVoltage(outputVolts);
        motor2.setVoltage(outputVolts);
    }

    @Override
    public void periodic() {
        motor1.periodic();
        motor2.periodic();
    }

    @Override
    public double get() {
        return motor1.get();
    }

    @Override
    public void setSafety(boolean on) {
        motor1.setSafety(on);
        motor2.setSafety(on);
    }

    @Override
    public void setInverted(boolean inverted) {
        motor1.setInverted(inverted);
        motor2.setInverted(inverted);
    }

    @Override
    public boolean getInverted() {
        return motor1.getInverted();
    }

    @Override
    public void disable() {
        motor1.disable();
        motor2.disable();
    }

    @Override
    public void stopMotor() {
        motor1.stopMotor();
        motor2.stopMotor();
    }

    @Override
    public MotorController getRawMotor() {

        throw new RuntimeException("Cannot Retrieve Single MotorController from MotorGroup. Use MotorGroup.motor1 and MotorGroup.motor2 instead.");
    }
}
