package frc.robot.control.tuple;

import frc.robot.Tuple2;

public class AutonomousDoubleControl extends DoubleControlStyle {
    double value1;
    double value2;
    @Override
    public Tuple2<Double> getValue() {
        return new Tuple2<>(value1, value2);
    }

    public void updateValue1(double v) {
        value1=v;
    }
    public void updateValue2(double v) {
        value2=v;
    }

    public void updateValue(double v1, double v2) {
        value1=v1;
        value2=v2;
    }
    public AutonomousDoubleControl(double v1, double v2) {
        value1=v1;
        value2=v2;
    }
}
