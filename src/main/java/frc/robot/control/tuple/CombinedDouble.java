package frc.robot.control.tuple;

import frc.robot.Tuple2;
import frc.robot.control.single.SingleControlStyle;

public class CombinedDouble extends DoubleControlStyle {
    private SingleControlStyle xStyle;
    private SingleControlStyle yStyle;
    @Override
    public Tuple2<Double> getValue() {
        return new Tuple2<>(xStyle.getValue(), yStyle.getValue());
    }

    public CombinedDouble(SingleControlStyle xStyle, SingleControlStyle yStyle) {
        this.xStyle=xStyle;
        this.yStyle=yStyle;
    }

    public void setxStyle(SingleControlStyle xStyle) {
        this.xStyle = xStyle;
    }

    public void setyStyle(SingleControlStyle yStyle) {
        this.yStyle = yStyle;
    }
}
