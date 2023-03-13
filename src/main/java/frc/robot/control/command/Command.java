package frc.robot.control.command;

import frc.robot.subsystem.Subsystem;

import java.util.ArrayList;

public abstract class Command {
    private ArrayList<Subsystem> subsystemList;

    protected void require(Subsystem c) {
        if (!subsystemList.contains(c)) {
            subsystemList.add(c);
        }
    }
    protected void require(ArrayList<Subsystem> s) {
        for (Subsystem c : s) {
            if (!subsystemList.contains(c)) {
                subsystemList.add(c);
            }
        }
    }
    public ArrayList<Subsystem> getRequiredSubsystems() {
        return subsystemList;
    }
    public Command() {
        subsystemList=new ArrayList<>();
    }

    public abstract void run();
    public abstract void start();
    public abstract void clean();
    public abstract boolean isFinished();
}
