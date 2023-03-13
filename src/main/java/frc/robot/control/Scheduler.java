package frc.robot.control;

import frc.robot.control.command.Command;
import frc.robot.subsystem.Subsystem;
import java.util.ArrayList;
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
            System.out.println("Running " + c);
            c.run();
            if (c.isFinished()) {
                System.out.println("Finishing " + c);
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
            activeCommands.add(c);
            c.start();
            System.out.println("Starting " + c);
        }
    }
}
