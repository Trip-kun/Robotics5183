package frc.robot.subsystem.hardware;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class SparkMotor {
    private Spark sparkMotor;

    public SparkMotor(int port) {
        sparkMotor = new Spark(port);
    }

    public void set(double speed) {
        sparkMotor.set(speed);
    }

    public void periodic() {
        sparkMotor.feed();
    }

    public double get() {
        return sparkMotor.get();
    }

    public void setSafety(boolean on) {
        sparkMotor.setSafetyEnabled(on);
    }

    public void setInverted(boolean inverted) {
        sparkMotor.setInverted(inverted);
    }

    public boolean getInverted() {
        return sparkMotor.getInverted();
    }

    public Spark getRawMotor() {
        return sparkMotor;
    }
}