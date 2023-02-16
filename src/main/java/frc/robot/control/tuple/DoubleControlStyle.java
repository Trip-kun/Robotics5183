package frc.robot.control.tuple;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Tuple2;
public abstract class DoubleControlStyle {
    protected XboxController xbox;
    public abstract Tuple2<Double> getValue();

    public DoubleControlStyle setXboxController(XboxController xbox) {
        this.xbox=xbox;
        return this;
    }
}
