package frc.robot.control.tuple;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Tuple2;
import frc.robot.control.Button;
import frc.robot.control.ButtonStyle;

public class DoubleButton extends DoubleControlStyle {
    private ButtonStyle style;
    private Button button;
    private double unpressedX;
    private double unpressedY;
    private double pressedX;
    private double pressedY;

    private boolean swapped=false;
    private boolean lastState=false;

    @Override
    public Tuple2<Double> getValue() {
        boolean buttonVal=getButtonValue();
        switch (style) {
            case Switch:
                lastState=buttonVal;
                if (buttonVal) {
                    swapped=true;
                    return new Tuple2<>(pressedX, pressedY);
                } else {
                    swapped=false;
                    return new Tuple2<>(unpressedX, unpressedY);
                }
            case Set:
                if (lastState==true && buttonVal==false) {
                    swapped=!swapped;
                    lastState= false;
                }
                if (swapped) {
                    return new Tuple2<>(pressedX, pressedY);
                } else {
                    return new Tuple2<>(unpressedX, unpressedY);
                }
            default:
                return new Tuple2<>(0.0, 0.0);
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

    public DoubleButton setStyle(ButtonStyle style) {
        this.style=style;
        return this;
    }
    public ButtonStyle getStyle() {return style;}

    public double getPressedX() {
        return pressedX;
    }

    public double getPressedY() {
        return pressedY;
    }

    public double getUnpressedX() {
        return unpressedX;
    }

    public double getUnpressedY() {
        return unpressedY;
    }

    public DoubleButton setUnpressedX(double unpressedX) {
        this.unpressedX = unpressedX;
        return this;
    }

    public DoubleButton setUnpressedY(double unpressedY) {
        this.unpressedY = unpressedY;
        return this;
    }
    public DoubleButton setUnpressed(Tuple2<Double> unpressedVal) {
        this.unpressedX=unpressedVal.val1;
        this.unpressedY=unpressedVal.val2;
        return this;
    }

    public DoubleButton setPressedX(double pressedX) {
        this.pressedX = pressedX;
        return this;
    }

    public DoubleButton setPressedY(double pressedY) {
        this.pressedY = pressedY;
        return this;
    }
    public DoubleButton setPressed(Tuple2<Double> pressedVal) {
        this.pressedX=pressedVal.val1;
        this.pressedY=pressedVal.val2;
        return this;
    }
    public DoubleButton(XboxController xbox, ButtonStyle style, Button button, double unpressedX, double unpressedY, double pressedX, double pressedY) {
        this.style=style;
        this.xbox=xbox;
        this.button=button;
        this.pressedX=pressedX;
        this.pressedY=pressedY;
        this.unpressedX=unpressedX;
        this.unpressedY=unpressedY;
    }
}
