package frc.robot.hardware.encoder;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class TalonFXEncoder extends Encoder{
    private TalonFX talonFX;
    public TalonFXEncoder(TalonFX tx) {
        talonFX =tx;
    }

    @Override
    public double getUnitsRadians() {
        return (talonFX.getSensorCollection().getIntegratedSensorPosition()/1024)*Math.PI;
    }

    @Override
    public double getUnitsRotations() {
        return (talonFX.getSensorCollection().getIntegratedSensorPosition()/2048);
    }

    @Override
    public double getUnitsDegrees() {
        return (talonFX.getSensorCollection().getIntegratedSensorPosition()/2048)*360;
    }
    @Override
    public void reset() {
        talonFX.getSensorCollection().setIntegratedSensorPosition(0, 0);
    }
}
