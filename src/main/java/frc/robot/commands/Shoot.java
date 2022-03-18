package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystem.Shooter;

public class Shoot extends Command {
    boolean isFinished = false;
    ShooterMode shooterMode;
    Shooter shooter;
    Command endCommand;
    Scheduler scheduler = Scheduler.getInstance();

    Timer timer = new Timer();
    @Override
    public void start() {
        timer.start();
    }
    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    public Shoot(Shooter shooter1, ShooterMode mode, Command command) {
        shooter=shooter1; shooterMode=mode; endCommand=command;
        requires(shooter1);
    }

    @Override
    public void execute() {
        switch (shooterMode) {
            case INTAKE:
                shooter.Intake();
                break;
            case SHOOT:
                shooter.Shoot();
                break;
        }

        if (shooterMode == ShooterMode.INTAKE && timer.get()>1.5) {
            isFinished=true;
            shooter.Reset();
            scheduler.add(endCommand);
        }

        if (shooterMode == ShooterMode.SHOOT && timer.get()>2) {
            isFinished=true;
            shooter.Reset();
            scheduler.add(endCommand);
        }
    }
}
