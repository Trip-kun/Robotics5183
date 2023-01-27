package frc.robot;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import frc.robot.subsystem.ControllerManager;
import frc.robot.subsystem.control.FullStick;
import frc.robot.subsystem.control.RampCurve;
import frc.robot.subsystem.control.StickMode;

public class RobotMap {

    // Basically Ethan's Code that I haven't taken the time to understand yet.
    public static SupplyCurrentLimitConfiguration driveCurrentLimit = new SupplyCurrentLimitConfiguration(true, 30, 0 , 0.2);
    public static final double DRIVE_SPEED_RAMP=0.2;

    // These are for FalconFX motors.
    public static final int UpperLeftMotor=1;
    public static final int UpperRightMotor=3;
    public static final int LowerLeftMotor=2;
    public static final int LowerRightMotor=0;

    public static final ControllerManager controllerManager = new ControllerManager().init();
    public static final FullStick driveTrainControl=new FullStick(controllerManager.getFirstController(), StickMode.rightX,StickMode.leftY, new RampCurve(RampCurve.Curve.Linear, 10.0, 0.09), new RampCurve(RampCurve.Curve.Linear, 5.0, 0.09) );
    //Gear Ratios, INCHES
    public static final double TalonGearbox=5.95;
    public static final int TalonDiameter = 6;


    public static final int ControllerNumber=0;
    public static final int Controller2Number=1;

    public static final double MaxSpeed=0.60;
}
