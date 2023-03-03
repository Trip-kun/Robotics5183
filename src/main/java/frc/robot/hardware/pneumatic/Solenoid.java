package frc.robot.hardware.pneumatic;

import edu.wpi.first.wpilibj.PneumaticsBase;

public class Solenoid {
    edu.wpi.first.wpilibj.Solenoid solenoid;
    public Solenoid(edu.wpi.first.wpilibj.Solenoid s) {
        solenoid=s;
    }
    public Solenoid(PneumaticsBase pneumaticsBase, int id) {
        solenoid=pneumaticsBase.makeSolenoid(id);
    }

    public void set(boolean on) {
        solenoid.set(on);
    }
    public boolean get() {
        return solenoid.get();
    }
}
