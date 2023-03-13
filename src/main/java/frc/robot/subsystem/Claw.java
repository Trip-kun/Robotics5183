package frc.robot.subsystem;

import frc.robot.control.single.SingleControlStyle;
import frc.robot.hardware.pneumatic.Solenoid;

public class Claw extends Subsystem {
    private SingleControlStyle style;
    private Solenoid claw;
    public void teleop() {
        control(style);
    }

    public void setStyle(SingleControlStyle style) {
        this.style = style;
    }

    public SingleControlStyle getStyle() {
        return style;
    }

    public Claw(Solenoid s, SingleControlStyle control) {
        style=control;
        claw=s;
    }
    private void control(SingleControlStyle control) {
        if (control.getValue()!=0) {
            claw.set(false);
        } else {
            claw.set(true);
        }
    }
    public void autonomous(SingleControlStyle control) {
        control(control);
    }
}
