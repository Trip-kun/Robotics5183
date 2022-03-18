package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystem.LiftSpool;


public class LiftSpoolControl extends Command {
    private LiftSpool spool;
    private SpoolDirection direction;
    private boolean isFinished=false;
    private Timer timer = new Timer();
    private Command endCommand;
    private Scheduler scheduler = Scheduler.getInstance();
    public LiftSpoolControl(LiftSpool Spool, SpoolDirection Direction, Command command) {
        spool=Spool; direction=Direction; endCommand=command;
        requires(spool);

    }

    @Override
    public synchronized void start() {
        timer.start();
    }

    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    @Override
    protected void execute() {
        switch (direction) {
            case UP:
                spool.setMotors(1);
                break;
            case DOWN:
                spool.setMotors(-1);
                break;
        }
        if (timer.get()>=3) {
            isFinished=true;
            scheduler.add(endCommand);
        }
    }
}
