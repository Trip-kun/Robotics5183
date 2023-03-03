package frc.robot.control.single;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.control.Button;
import frc.robot.control.ButtonStyle;

public class SingleButton extends SingleControlStyle {
    private ButtonStyle style;
    private Button button;
    private double unpressed=0;
    private double pressed=1;
    private boolean swapped=false;
    private boolean lastState=false;
    @Override
    public double getValue() {
        boolean buttonVal=getButtonValue();
        switch (style) {
            case Switch:
                lastState=buttonVal;
                if (buttonVal) {
                    swapped=true;
                    return pressed;
                } else {
                    swapped=false;
                    return unpressed;
                }
            case Set:
                if (lastState && !buttonVal) {
                    swapped=!swapped;
                    lastState= false;
                }
                if (swapped) {
                    return pressed;
                } else {
                    return unpressed;
                }
            default:
                return 0;
        }

    }
    private boolean getButtonValue() {
        switch(button) {
            case A:
                return xbox.getAButton();
            case B:
                return xbox.getBButton();
            case X:
                return xbox.getXButton();
            case Y:
                return xbox.getYButton();
            case Start:
                return xbox.getStartButton();
            case Select:
                return xbox.getBackButton();
            case LeftBumper:
                return xbox.getLeftBumper();
            case LeftStick:
                return xbox.getLeftStickButton();
            case RightBumper:
                return xbox.getRightBumper();
            case RightStick:
                return xbox.getRightStickButton();
            default:
                return false;
        }
    }

    public SingleButton setStyle(ButtonStyle style) {
        this.style = style;
        return this;
    }

    public ButtonStyle getStyle() {
        return style;
    }

    public double getPressed() {
        return pressed;
    }

    public double getUnpressed() {
        return unpressed;
    }

    public SingleButton setPressed(double pressed) {
        this.pressed = pressed;
        return this;
    }

    public SingleButton setUnpressed(double unpressed) {
        this.unpressed = unpressed;
        return this;
    }
    public SingleButton(XboxController xbox, ButtonStyle style, Button button, double unpressedVal, double pressedVal) {
        this.style=style;
        this.xbox=xbox;
        this.button=button;
        this.unpressed=unpressedVal;
        this.pressed=pressedVal;
    }

    public SingleButton setButton(Button button) {
        this.button = button;
        return this;
    }

    public Button getButton() {
        return button;
    }

    @Override
    public SingleButton setXboxController(XboxController xbox) {
        xbox=xbox;
        return this;

    }

}
