package frc.robot.subsystem.hardware;

import edu.wpi.first.wpilibj.SpeedController;

public abstract class Motor {
    public abstract void setSpeed(double speed);
    public abstract void periodic();
    public abstract double getSpeed();
    public abstract void setSafety(boolean on);
    public abstract void setInverted(boolean inverted);
    public abstract boolean getInverted();
    public abstract SpeedController getRawMotor();
}
