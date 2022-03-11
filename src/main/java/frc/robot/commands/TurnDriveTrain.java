package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveTrain;

// ALL ANGLES ARE IN RADIANS UNLESS OTHERWISE STATED

public class TurnDriveTrain extends Command {
    // Starting angle in relation to an absolute angle [See TurnModes.java]
    private static double startingAngle = 0;
    // Current
    private double angle = 0;

    private boolean is_finished =false;

    private DriveTrain driveTrain;
    public TurnDriveTrain(TurnModes mode, double new_angle, DriveTrain drivetrain ) {
        requires(drivetrain);
        driveTrain=drivetrain;
    }
    @Override
    protected boolean isFinished() {
        return is_finished;
    }
    @Override
    public void execute() {
        /*
            Get current rotation using gyroscope
            IF current rotation matches end rotation: isFinished = true AND return/skip remaining code.
            Set Motor speed respectively to rotate in place




         */
    }
    // To Be Used When Choosing an Autonomous Command to help determine rotation on the field
    private static void setStartingAngle(double angle) {
        startingAngle = angle;
    }
}
