package frc.robot.hardware.pneumatic;

import edu.wpi.first.wpilibj.CompressorConfigType;
import edu.wpi.first.wpilibj.PneumaticsBase;

public class Compressor {
    edu.wpi.first.wpilibj.Compressor compressor;
    public Compressor(edu.wpi.first.wpilibj.Compressor c) {
        compressor=c;
    }
    public Compressor(PneumaticsBase pneumaticsBase) {
        compressor= pneumaticsBase.makeCompressor();
    }

    public void enable() {
        compressor.enableDigital();
    }
    public void enableAnalog(double low, double high) {
        compressor.enableAnalog(low, high);
    }
    public void disable() {
        compressor.disable();
    }
    public boolean isEnabled() {
        return compressor.isEnabled();
    }

    public CompressorConfigType getMode() {
        return compressor.getConfigType();
    }
}
