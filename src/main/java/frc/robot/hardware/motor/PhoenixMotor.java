package frc.robot.hardware.motor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

public abstract class PhoenixMotor extends Motor {
    protected BaseMotorController motor;
    public void follow(PhoenixMotor master, InvertType inverted) {
        motor.follow(master.getRawMasterMotor());
        motor.setInverted(inverted);
    }

    public abstract BaseMotorController getRawMasterMotor();

    public void setDeadband(double deadband) {
        motor.configNeutralDeadband(deadband);
    }
    public void set(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }
    public double get() {
        return motor.getMotorOutputPercent();
    }

    public boolean getInverted() {
        return motor.getInverted();
    }
}
