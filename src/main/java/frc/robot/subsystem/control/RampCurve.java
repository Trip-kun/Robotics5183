package frc.robot.subsystem.control;


import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Util;

public class RampCurve {
    public enum Curve {
        Linear, Exponential
    };
    private Curve mode = Curve.Linear;
    private double exaggeration=1.0;
    private double deadZone=0.09;
    public double curve(double x) {
        switch (mode) {
            case Linear:
                if (Math.abs(x)<deadZone) {
                    return 0;
                }
                return x;
            case Exponential:
                if (Math.abs(x)<deadZone) {
                    return 0;
                } else {
                    return ((x/Math.abs(x))*Math.pow(1+exaggeration, Math.abs(x))-1)/exaggeration;
                }
            default:
                return 0;
        }
    }

    public RampCurve setExaggeration(double exaggeration) {
        this.exaggeration = exaggeration;
        return this;
    }
    public RampCurve setDeadZone(double deadZone) {
        this.deadZone=deadZone;
        return this;
    }
    public RampCurve setMode(Curve mode) {
        this.mode=mode;
        return this;
    }
    public RampCurve() {}
    public RampCurve(Curve mode) {
        this.mode=mode;
    }
    public RampCurve(Curve mode, double exaggeration) {
        this.mode=mode;
        this.exaggeration=exaggeration;
    }
    public RampCurve(Curve mode, double exaggeration, double deadZone) {
        this.mode=mode;
        this.exaggeration=exaggeration;
        this.deadZone=deadZone;
    }
}

