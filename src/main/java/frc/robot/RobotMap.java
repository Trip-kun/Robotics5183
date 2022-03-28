package frc.robot;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

public class RobotMap {
    /*
    Config 0: Romi Robot
    Config 1: MS Paint Robot
    config 2: Steve Robot
     */
    public static final int config = 2;
    public static final boolean safety = true;
    // Basically Ethan's Code that I haven't taken the time to understand yet.
    public static SupplyCurrentLimitConfiguration driveCurrentLimit = new SupplyCurrentLimitConfiguration(true, 30, 0 , 0.2);
    public static final double DRIVE_SPEED_RAMP=0.2;

    // These are for FalconFX motors.
    public static final int UpperLeftMotor=3;
    public static final int UpperRightMotor=2;
    public static final int LowerLeftMotor=5;
    public static final int LowerRightMotor=4;

    //RPM, INCHES
    public static final double TalonGearbox=5.95;
    public static final int TalonDiameter = 6;
    // These are for Spark motors.
    public static final int leftMotor=1;
    public static final int rightMotor=2;

    public static final int spoolMotor=1;
    public static final int rotatorMotor=0;
    public static final int leftShooterMotor=2;
    public static final int rightShooterMotor=3;


    public static final int ControllerNumber=0;
    public static final int Controller2Number=1;
}
