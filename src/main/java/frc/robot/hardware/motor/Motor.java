package frc.robot.hardware.motor;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public abstract class Motor implements MotorController {
    public abstract void set(double speed);
    public abstract void periodic();
    public abstract double get();
    public abstract void setSafety(boolean on);
    public abstract void setInverted(boolean inverted);
    public abstract boolean getInverted();
    public abstract MotorController getRawMotor();

}
