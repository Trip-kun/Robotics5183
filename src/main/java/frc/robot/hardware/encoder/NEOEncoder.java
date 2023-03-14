package frc.robot.hardware.encoder;

import frc.robot.hardware.motor.SparkMaxMotor;
import com.revrobotics.SparkMaxAbsoluteEncoder;
public class NEOEncoder extends Encoder {
    SparkMaxAbsoluteEncoder encoder;
    public NEOEncoder(SparkMaxMotor spark) {
        encoder = spark.getTrueRawMotor().getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle);
    }
    @Override
    public double getUnitsRadians() {
        return encoder.getPosition()*2*Math.PI;
    }

    @Override
    public double getUnitsRotations() {
        return encoder.getPosition();
    }

    @Override
    public double getUnitsDegrees() {
        return encoder.getPosition()*360;
    }

    @Override
    public void reset() {

    }
}
