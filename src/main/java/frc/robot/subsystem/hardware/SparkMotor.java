package frc.robot.subsystem.hardware;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

public class SparkMotor extends Motor {
    private Spark sparkMotor;
    public SparkMotor(int port) {
        sparkMotor = new Spark(port);
    }
    @Override
    public void setSpeed(double speed) {
        sparkMotor.setSpeed(speed);
    }

    @Override
    public void periodic() {
        sparkMotor.feed();
    }

    @Override
    public double getSpeed() {
        return sparkMotor.getSpeed();
    }

    @Override
    public void setSafety(boolean on) {
        sparkMotor.setSafetyEnabled(on);
    }

    @Override
    public void setInverted(boolean inverted) {
        sparkMotor.setInverted(inverted);
    }

    @Override
    public boolean getInverted() {
        return sparkMotor.getInverted();
    }

    @Override
    public SpeedController getRawMotor() {
        return sparkMotor;
    }
}