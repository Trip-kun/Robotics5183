package frc.robot.hardware.pneumatic;

import edu.wpi.first.wpilibj.PneumaticsBase;

public class DoubleSolenoid {
    edu.wpi.first.wpilibj.DoubleSolenoid solenoid;
    public DoubleSolenoid(edu.wpi.first.wpilibj.DoubleSolenoid s) {
        solenoid = s;
    }
    public DoubleSolenoid(PneumaticsBase pneumaticsBase, int idForward, int idReverse) {
        solenoid = pneumaticsBase.makeDoubleSolenoid(idForward, idReverse);
    }

    public enum SolenoidMode {
        Forward, Reverse, Off
    }
    public void set (SolenoidMode mode) {
        switch(mode) {
            case Reverse:
                solenoid.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse);
                break;
            case Forward:
                solenoid.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward);
                break;
            case Off:
                solenoid.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff);
                break;
        }
    }
}
