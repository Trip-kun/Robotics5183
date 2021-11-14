package frc.robot.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class RomiInputOutput {
    private DigitalInput buttonA;
    private DigitalInput buttonB;
    private DigitalInput buttonC;
    private DigitalOutput greenLight;
    private DigitalOutput yellowLight;
    private DigitalOutput redLight;
    public RomiInputOutput () {
        buttonA = new DigitalInput(0);
        buttonB = new DigitalInput(1);
        buttonC = new DigitalInput(2);
        greenLight = new DigitalOutput(1);
        yellowLight = new DigitalOutput(2);
        redLight = new DigitalOutput(3);
    }
    public boolean getButtonA() {
        return buttonA.get();
    }
    public boolean getButtonB() {
        return buttonB.get();
    }
    public boolean getButtonC() {
        return buttonC.get();
    }
    public void setGreenLight(boolean val) {
        greenLight.set(val);
    }
    public void setYellowLight(boolean val) {
        yellowLight.set(val);
    }
    public void setRedLight(boolean val) {
        redLight.set(val);
    }
}
