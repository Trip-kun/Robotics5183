package frc.robot.control;


import edu.wpi.first.wpilibj.XboxController;

public class HalfStick extends SingleControlStyle {
    private RampCurve curve= new RampCurve(RampCurve.Curve.Linear);
    private StickMode mode;
    private double MaxSpeed=1;
    @Override
    public double getValue() {
        return MaxSpeed*curve.curve(getStickValue());
    }
    private double getStickValue() {
        switch(mode) {
            case leftX:
                return xbox.getLeftX();
            case leftY:
                return xbox.getLeftY();
            case rightX:
                return xbox.getRightX();
            case rightY:
                return xbox.getRightY();
            default:
                return 0;
        }
    }

    public HalfStick(XboxController xbox, StickMode mode) {
        this.xbox=xbox; this.mode=mode;
    }
    public HalfStick(XboxController xbox, StickMode mode, RampCurve curve) {
        this.mode=mode;
        this.xbox=xbox;
        this.curve=curve;
    }

    public void setCurve(RampCurve curve) {
        this.curve = curve;
    }

    public RampCurve getCurve() {
        return curve;
    }

    public StickMode getMode() {
        return mode;
    }

    public void setMode(StickMode mode) {
        this.mode = mode;
    }

    public void setMaxSpeed(double maxSpeed) {
        MaxSpeed = maxSpeed;
    }

    public double getMaxSpeed() {
        return MaxSpeed;
    }
}
