package frc.robot.control.command;
import frc.robot.control.single.AutonomousSingleControl;
import frc.robot.control.single.SingleControlStyle;
import frc.robot.subsystem.Claw;

import java.time.Duration;
import java.time.Instant;

public class SetClaw extends Command {
    private boolean mode;
    private Claw claw;
    private Instant start;
    private SingleControlStyle oldStyle;
    private AutonomousSingleControl dummy;
    private boolean isFinished=false;
    @Override
    public void run() {
        int x=0;
        if (mode) {
            x=1;
        }
        Duration d = Duration.between(start, Instant.now());
        if (d.toSeconds()>1) {
            isFinished=true;
        }
        dummy.setValue(x);
    }

    @Override
    public void start() {
        oldStyle=claw.getStyle();
        dummy = new AutonomousSingleControl(0);
        claw.setStyle(dummy);
        start = Instant.now();
    }

    @Override
    public void clean() {
        claw.setStyle(oldStyle);
        claw.autonomous(oldStyle);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
    public SetClaw(Claw claw, boolean on) {
        mode=on;
        require(claw);
        this.claw=claw;
    }
}
