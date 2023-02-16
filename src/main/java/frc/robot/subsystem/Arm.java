package frc.robot.subsystem;

import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.hardware.motor.Motor;
import frc.robot.hardware.pneumatic.DoubleSolenoid;

public class Arm {
    public Motor base;
    public DoubleSolenoid joint;
    DoubleControlStyle controlStyle;

    public Arm(Motor motorBase, DoubleSolenoid jointSolenoid, DoubleControlStyle style) {
        base=motorBase;
        joint=jointSolenoid;
        controlStyle=style;
    }

    public void teleop() {
        control(controlStyle);
    }

    private void control(DoubleControlStyle controlStyle) {
        base.set(controlStyle.getValue().val1);
        if (controlStyle.getValue().val2<0) {
            joint.set(DoubleSolenoid.SolenoidMode.Reverse);
        } else if (controlStyle.getValue().val2>0) {
            joint.set(DoubleSolenoid.SolenoidMode.Forward);
        } else {
            joint.set(DoubleSolenoid.SolenoidMode.Off);
        }
    }

    public void autonomous(DoubleControlStyle controlStyle) {
        control(controlStyle);
    }
    public void periodic() {
        base.periodic();
    }

}
