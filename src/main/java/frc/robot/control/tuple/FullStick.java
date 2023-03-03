package frc.robot.control.tuple;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Tuple2;
import frc.robot.control.RampCurve;
import frc.robot.control.StickMode;
import frc.robot.control.tuple.DoubleControlStyle;

public class FullStick extends DoubleControlStyle {
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
            case hatX:
                int x = xbox.getPOV();
                if (x==0 || x==45 || x==315) {
                    return 1;
                } else if (x==180 || x==135 || x==225) {
                    return -1;
                } else {
                    return 0;
                }
            case hatY:
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
