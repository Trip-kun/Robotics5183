package frc.robot.hardware.encoder;

import com.ctre.phoenix.sensors.CANCoder;

public class CANEncoder extends Encoder {
    private CANCoder encoder;
    public CANEncoder(int id) {
        encoder = new CANCoder(id);
    }
    public CANEncoder(int id, String canbus) {
        encoder = new CANCoder(id, canbus);
    }
    @Override
    public double getUnitsRadians() {
        return (encoder.getPosition()/180)*Math.PI;
    }

    @Override
    public double getUnitsRotations() {
        return encoder.getPosition()/360;
    }

    @Override
    public double getUnitsDegrees() {
        return encoder.getPosition();
    }
    @Override
    public void reset() {
        encoder.setPosition(0);
    }
}
