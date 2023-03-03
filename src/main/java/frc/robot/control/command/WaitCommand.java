package frc.robot.control.command;

import frc.robot.subsystem.Subsystem;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class WaitCommand extends Command {
    private boolean finished=false;
    private double needed;
    Instant start;
    @Override
    public void run() {
        Duration d = Duration.between(start, Instant.now());
        if(d.toSeconds()>needed) {
            finished=true;
        }
    }

    @Override
    public void start() {
        start=Instant.now();
    }

    @Override
    public void clean() {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
    public WaitCommand(double duration, Subsystem stall) {
        require(stall);
        needed=duration;
    }
    public WaitCommand(double duration, ArrayList<Subsystem> stall) {
        require(stall);
        needed=duration;
    }
}
