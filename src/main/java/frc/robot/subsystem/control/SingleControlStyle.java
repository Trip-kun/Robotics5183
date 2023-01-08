package frc.robot.subsystem.control;

import edu.wpi.first.wpilibj.XboxController;

public abstract class SingleControlStyle {
    protected XboxController xbox;
    public abstract double getValue();
    public SingleControlStyle setXboxController(XboxController xbox) {
        this.xbox=xbox;
        return this;
    }
}
