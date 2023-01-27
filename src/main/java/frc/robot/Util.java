package frc.robot;

public class Util {
    public static double clamp(double x, double upper, double lower) {
        return Math.min(Math.max(x, lower), upper);
    }
    public static double normalize_angle_degrees(double angle) {
        if (angle < 0) {
            return normalize_angle_degrees(angle+360);
        } else if (angle >= 360) {
            return normalize_angle_degrees(angle-360);
        } else {
            return angle;
        }
    }

}
