package frc.robot.hardware.motor;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class SparkMaxMotor extends Motor {
    CANSparkMax sMotor;
    public SparkMaxMotor(int id) {
        sMotor = new CANSparkMax(id, MotorType.kBrushless);
    }
    @Override
    public void set(double speed) {
        sMotor.set(speed);
    }

    @Override
    public void periodic() {

    }

    @Override
    public double get() {
        return sMotor.get();
    }

    @Override
    public void setSafety(boolean on) {

    }

    @Override
    public void setInverted(boolean inverted) {
        sMotor.setInverted(inverted);
    }

    @Override
    public boolean getInverted() {
        return sMotor.getInverted();
    }

    @Override
    public void disable() {
        sMotor.disable();
    }

    @Override
    public void stopMotor() {
        sMotor.stopMotor();
    }

    @Override
    public MotorController getRawMotor() {
        return sMotor;
    }
    public CANSparkMax getTrueRawMotor() {
        return sMotor;
    }

}
