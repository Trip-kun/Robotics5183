package frc.robot.hardware.gyro;

public abstract class SingleAxisGyroscope {
    public abstract double getDegrees();
    public abstract double getRadians();

    public abstract double getRotations();
    public abstract void calibrate();
    public abstract void reset();
    public enum Axis {
        YAW, PITCH, ROLL
    }
}
