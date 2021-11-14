package frc.robot.hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.hardware.Motor;

public class TalonFXMotor extends Motor {
    private WPI_TalonFX talonMotor;
    public TalonFXMotor(int id) {
        talonMotor = new WPI_TalonFX(id);
    }
    @Override
    public void setSpeed(double speed) {
        talonMotor.set(speed);
    }
    @Override
    public WPI_TalonFX getRawMotor() {
        return talonMotor;
    }
    @Override
    public void periodic() {
        talonMotor.feed();
    }

    @Override
    public double getSpeed() {
        return talonMotor.get();
    }
    @Override
    public void setSafety(boolean on) {
        talonMotor.setSafetyEnabled(on);
    }
    @Override
    public void setInverted(boolean inverted) {
        talonMotor.setInverted(inverted);
    }
    @Override
    public boolean getInverted() {
        return talonMotor.getInverted();
    }

}
