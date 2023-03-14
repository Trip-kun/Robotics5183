package frc.robot.hardware.gyro;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.robot.Util;

public class SPIGyroscope extends SingleAxisGyroscope{
    ADXRS450_Gyro gyro;

    public SPIGyroscope(ADXRS450_Gyro Gyro) {
        gyro = Gyro;
    }

    @Override
    public double getDegrees() {
        return getAngle();
    }

    @Override
    public double getRadians() {
        return getAngle()*Math.PI/180;
    }

    @Override
    public double getRotations() {
        return getAngle()/360;
    }

    public void calibrate() {
        gyro.calibrate();
    }

    public void reset() {
        gyro.reset();
    }

    public double getAngle() {
        int x = (int) (gyro.getAngle() * 10);
        return x / 10;
    }

    // DEGREES PER SECOND
    public double getRate() {
        return gyro.getRate();
    }

}