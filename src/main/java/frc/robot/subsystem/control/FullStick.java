package frc.robot.subsystem.control;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Tuple2;

public class FullStick extends DoubleControlStyle{
    private RampCurve xCurve=new RampCurve(RampCurve.Curve.Linear);
    private RampCurve yCurve = new RampCurve(RampCurve.Curve.Linear);
    private StickMode xMode;
    private StickMode yMode;
    private double MaxSpeed=1;
    @Override
    public Tuple2<Double> getValue() {
        return new Tuple2<>(xCurve.curve(getStickValue(xMode)), yCurve.curve(getStickValue(yMode)));
    }
    private double getStickValue(StickMode mode) {
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
    public FullStick(XboxController xbox, StickMode xMode, StickMode yMode) {
        this.xbox=xbox;
        this.xMode=xMode;
        this.yMode=yMode;
    }
    public FullStick(XboxController xbox, StickMode xMode, StickMode yMode, RampCurve xCurve, RampCurve yCurve) {
        this.xbox=xbox;
        this.xMode=xMode;
        this.yMode=yMode;
        this.xCurve=xCurve;
        this.yCurve=yCurve;
    }
}
