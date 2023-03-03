package frc.robot.subsystem;

import edu.wpi.first.wpilibj.PneumaticsBase;
import frc.robot.control.single.SingleControlStyle;
import frc.robot.hardware.pneumatic.Compressor;

public class PneumaticBase extends Subsystem {
    private PneumaticsBase pneumaticsBase;
    public Compressor compressor;
    private SingleControlStyle controlStyle;
    private boolean enabled=false;

    public PneumaticBase(PneumaticsBase pneumaticsBase, SingleControlStyle controlStyle) {
        this.pneumaticsBase=pneumaticsBase;
        this.controlStyle=controlStyle;
    }
    private void control(SingleControlStyle controlStyle) {
        if (controlStyle.getValue()!=0 && !enabled) {
            compressor.enable();
            enabled=true;
        } else if (controlStyle.getValue()==0 && enabled) {
            compressor.disable();
            enabled=false;
        }
    }
    public void teleop() {
        control(controlStyle);
    }
    public void autonomous(SingleControlStyle controlStyle) {
        control(controlStyle);
    }
    public void periodic() {}
}
