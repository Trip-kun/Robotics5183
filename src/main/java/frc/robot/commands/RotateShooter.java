package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystem.ShooterRotator;

public class RotateShooter extends Command {
    private ShooterRotator rotator;
    private boolean isFinished=false;
    private Command endCommand;
    private Direction direction;
    private Timer timer = new Timer();
    private Scheduler scheduler = Scheduler.getInstance();
    @Override
    protected boolean isFinished() {
        return isFinished;
    }

    public RotateShooter(Direction direction1, ShooterRotator shooterRotator, Command command)
    {
    direction=direction1; endCommand=command; rotator=shooterRotator;
    requires(rotator);
    }
    @Override
    public void start() {
        timer.start();
    }
    @Override
    public void execute() {
        switch (direction) {
            case CLOCKWISE:
                rotator.setMotor(1);
                break;
            case COUNTERCLOCKWISE:
                rotator.setMotor(-1);
                break;
        }
        if (timer.get()>=1) {
            isFinished=true;
            rotator.setMotor(0);
            scheduler.add(endCommand);
        }
    }
}
