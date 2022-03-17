package frc.robot.subsystem.hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TalonFXMotor {
    private WPI_TalonFX talonMotor;

    public TalonFXMotor(int id) {
        talonMotor = new WPI_TalonFX(id);
    }

    public void set(double speed) {
        talonMotor.set(speed);
    }

    public WPI_TalonFX getRawMotor() {
        return talonMotor;
    }

    public void periodic() {
        talonMotor.feed();
    }

    public double get() {
        return talonMotor.get();
    }

    public void setSafety(boolean on) {
        talonMotor.setSafetyEnabled(on);
    }

    public void setInverted(boolean inverted) {
        talonMotor.setInverted(inverted);
    }

    public boolean getInverted() {
        return talonMotor.getInverted();
    }

}
