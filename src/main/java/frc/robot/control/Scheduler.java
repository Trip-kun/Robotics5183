package frc.robot.control;

import frc.robot.control.command.Command;
import frc.robot.subsystem.Subsystem;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class Scheduler {
    private ArrayList<Command> temp = new ArrayList<>();
    private ArrayList<Subsystem> activeSubsystems = new ArrayList<>();
    private LinkedList<Command> commandQueue = new LinkedList<>();
    private ArrayList<Command> activeCommands = new ArrayList<>();



    public void scheduleCommand(Command c) {
            commandQueue.add(c);
    }

    public void run() {
        activeSubsystems.clear();
        temp.clear();
        for (Command c : activeCommands) {
            c.run();
            if (c.isFinished()) {
                c.clean();
                temp.add(c);
            } else {
                for (Subsystem s : c.getRequiredSubsystems()) {
                    if (!activeSubsystems.contains(s)) {
                        activeSubsystems.add(s);
                    }
                }
            }
        }
        for (Command c : temp) {
            activeCommands.remove(c);
        }
        temp.clear();
        
            for (Command c : commandQueue) {
                boolean okay = true;
                for (Subsystem s : c.getRequiredSubsystems()) {
                    if (activeSubsystems.contains(s)) {
                        okay = false;
                    }
                }
                if (okay) {
                    temp.add(c);
                    for (Subsystem s : c.getRequiredSubsystems()) {
                        if (!activeSubsystems.contains(s)) {
                            activeSubsystems.add(s);
                        }
                    }
                }
            }
        for (Command c: temp) {
            commandQueue.remove(c);
            c.start();
        }
    }
}
