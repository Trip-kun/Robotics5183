package frc.robot.hardware.gyro;

import edu.wpi.first.wpilibj.ADIS16448_IMU;

public class ADISAxisGyroscope extends SingleAxisGyroscope {
    public ADIS16448_IMU gyro;
    public Axis axis;

    public ADISAxisGyroscope(ADIS16448_IMU gyro, Axis axis) {
        this.gyro=gyro;
        this.axis=axis;
    }

    @Override
    public double getDegrees() {
        switch(axis) {
            case YAW:
                return gyro.getGyroAngleZ();
            case PITCH:
                return gyro.getGyroAngleY();
            case ROLL:
                return gyro.getGyroAngleX();
            default:
                return 0;
        }
    }

    @Override
    public double getRadians() {
        return getDegrees()*Math.PI/180;
    }

    @Override
    public double getRotations() {
        return getDegrees()/360;
    }

    @Override
    public void calibrate() {
        gyro.calibrate();
    }

    @Override
    public void reset() {
        gyro.reset();
    }
}
