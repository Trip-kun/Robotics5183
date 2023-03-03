package frc.robot.hardware.encoder;

public abstract class Encoder {
    public abstract double getUnitsRadians();
    public abstract double getUnitsRotations();
    public abstract double getUnitsDegrees();
    public abstract void reset();
}
