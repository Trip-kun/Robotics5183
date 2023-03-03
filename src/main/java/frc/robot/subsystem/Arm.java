package frc.robot.subsystem;

import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.hardware.encoder.Encoder;
import frc.robot.hardware.motor.Motor;
import frc.robot.hardware.pneumatic.DoubleSolenoid;

public class Arm extends Subsystem {
    public Motor base;
    public DoubleSolenoid joint;
    DoubleControlStyle controlStyle;
    public Encoder coder;

    public Arm(Motor motorBase, DoubleSolenoid jointSolenoid, DoubleControlStyle style, Encoder encoder) {
        base=motorBase;
        joint=jointSolenoid;
        controlStyle=style;
        coder=encoder;
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

    public DoubleControlStyle getControlStyle() {
        return controlStyle;
    }
}
