package frc.robot.subsystem.hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class VictorSPXMotor extends PhoenixMotor {

    WPI_VictorSPX motor;
    public VictorSPXMotor(int id) {
        motor = new WPI_VictorSPX(id);
    }
    public void setVoltage(double outputVolts) {
        motor.setVoltage(outputVolts);
    }
    public WPI_VictorSPX getRawMotor() {
        return motor;
    }
    public WPI_VictorSPX getRawMasterMotor() {
        return getRawMotor();
    }
    public void periodic() {
        motor.feed();
    }
    public void setSafety(boolean on) {
        motor.setSafetyEnabled(on);
    }
    @Override
    public void disable() {
        motor.disable();
    }
    @Override
    public void stopMotor() {
        motor.stopMotor();
    }
}
