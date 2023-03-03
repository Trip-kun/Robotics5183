package frc.robot.control.single;

public class AutonomousSingleControl extends SingleControlStyle {
    double value;
    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double v) {
        value=v;
    }

    public AutonomousSingleControl(double v) {
        value=v;
    }
}
