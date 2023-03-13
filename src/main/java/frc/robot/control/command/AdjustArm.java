package frc.robot.control.command;

import frc.robot.control.single.AutonomousSingleControl;
import frc.robot.control.single.SingleControlStyle;
import frc.robot.control.tuple.AutonomousDoubleControl;
import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.subsystem.Arm;

import java.time.Duration;
import java.time.Instant;

public class AdjustArm extends Command{
    enum Direction {
        EXTEND, RETRACT
    }
    Arm arm;
    double timePiston;
    double timeSpool;
    boolean spoolFinished=false;
    boolean pistonFinished=false;
    Direction piston;
    Direction spool;
    Instant start;
    AutonomousDoubleControl dummy = new AutonomousDoubleControl(0,0);
    DoubleControlStyle style;
    double speed;
    public AdjustArm(Arm arm, double piston, double spool, Direction armMovement, Direction spoolMovement, double speed) {
        this.arm=arm;
        timePiston=piston;
        timeSpool=spool;
        this.piston=armMovement;
        this.spool=spoolMovement;
        this.speed=speed;
    }
    @Override
    public void run() {
        if (Duration.between(start, Instant.now()).toSeconds()>timePiston) {
            pistonFinished=true;
        }
        if (arm.coder.getUnitsRadians()*0.5>timeSpool) {
            spoolFinished=true;
        }
        if (pistonFinished) {
            dummy.updateValue1(0);
        } else {
            if (piston==Direction.EXTEND) {
                dummy.updateValue1(1);
            } else {
                dummy.updateValue1(-1);
            }
        }
        if (spoolFinished) {
            dummy.updateValue2(0);
        } else {
            if (spool==Direction.EXTEND) {
                dummy.updateValue2(speed);
            } else {
                dummy.updateValue2(-speed);
            }
        }
        arm.autonomous(dummy);

    }

    @Override
    public void start() {
        start=Instant.now();
        style=arm.getControlStyle();
    }

    @Override
    public void clean() {
        arm.setControlStyle(style);
        arm.autonomous(style);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
