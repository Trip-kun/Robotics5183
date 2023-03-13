package frc.robot.control.single;


import edu.wpi.first.wpilibj.XboxController;
import frc.robot.control.RampCurve;
import frc.robot.control.StickMode;
import frc.robot.control.single.SingleControlStyle;

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
            case hatY:
                int x = xbox.getPOV();
                if (x==0 || x==45 || x==315) {
                    return 1;
                } else if (x==180 || x==135 || x==225) {
                    return -1;
                } else {
                    return 0;
                }
            case hatX:
                int y = xbox.getPOV();
                if (y==90 || y==45 || y==135) {
                    return 1;
                } else if (y==270 || y==315 || y==225) {
                    return -1;
                } else {
                    return 0;
                }
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
