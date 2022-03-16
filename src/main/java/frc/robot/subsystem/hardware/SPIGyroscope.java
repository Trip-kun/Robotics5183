package frc.robot.subsystem.hardware;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.Util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SPIGyroscope {
    ADXRS450_Gyro gyro;

    public SPIGyroscope(ADXRS450_Gyro Gyro) {
        gyro=Gyro;
    }
    public void calibrate() {
        gyro.calibrate();
    }
    public void reset() {
        gyro.reset();
    }
    public double getAngle() {
        int x = (int) (gyro.getAngle() * 1);
        double y = x/1;
        return Util.normalize_angle_degrees(y);
    }
    //DEGREES PER SECOND
    public double getRate() {
        return gyro.getRate();
    }

    public Rotation2d getRotation2d() {
        return gyro.getRotation2d();
    }

}