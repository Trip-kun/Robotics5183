package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystem.DriveTrain;

public class MoveDriveTrainByTime extends Command {
    private double Time;
    private Timer timer;
    private boolean isFinished = false;
    private double Speed;
    private boolean Forwards;
    private DriveTrain driveTrain;
    public MoveDriveTrainByTime(double time, double speed, boolean forwards, DriveTrain drivetrain) {
        Time=time;
        Speed=speed;
        Forwards=forwards;
        requires(drivetrain);
        driveTrain=drivetrain;
    }
    public void execute() {
        /*
            Compare Timer to Time. If Over/Equal, Set Motors to 0 and isFinished to true.
            Else, Set Motors to Speed and +/- based on Forwards.


         */
    }
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
