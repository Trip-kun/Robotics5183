package frc.robot;

public class Util {
    public static double clamp(double x, double upper, double lower) {
        return Math.min(Math.max(x, lower), upper);
    }
}
