package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Util;
import frc.robot.subsystem.PhoenixDriveTrain;

import static frc.robot.commands.Direction.CLOCKWISE;
import static frc.robot.commands.Direction.COUNTERCLOCKWISE;

// ALL ANGLES ARE IN DEGREES UNLESS OTHERWISE STATED

// BLUE IS 0 DEGREES
public class TurnDriveTrain extends Command {
    // Starting angle in relation to an absolute angle [See TurnModes.java]
    private static double startingAngle = 0;
    // Current
    private double t;
    private double newAngle;
    Command endCommand = null;
    private boolean is_finished = false;
    private Direction direction;
    private PhoenixDriveTrain phoenixDriveTrain;
    private double MaxSpeed = 1;
    private boolean started = false;
    TurnDriveTrainThread driveTrainThread;
    private Scheduler scheduler = Scheduler.getInstance();
    public TurnDriveTrain(TurnModes mode, double new_angle, PhoenixDriveTrain drivetrain, double maxSpeed, Command command) {
        requires(drivetrain);
        endCommand = command;
        phoenixDriveTrain = drivetrain;
        startingAngle = Util.normalize_angle_degrees(drivetrain.gyroscope.getAngle());
        MaxSpeed = maxSpeed;
        switch (mode) {
            case ABSOLUTE:
                newAngle = new_angle;
                break;
            case RELATIVE:
                newAngle = Util.normalize_angle_degrees(startingAngle + new_angle);
                break;
        }
        double x = Util.normalize_angle_degrees(newAngle - startingAngle);
        double y = x - 360;
        double AbsX = Math.abs(x);
        double AbsY = Math.abs(y);

        if (AbsX < AbsY) {
            if (x < 0) {
                direction = CLOCKWISE;
                t = x;
            } else {
                direction = COUNTERCLOCKWISE;
                t = x;
            }
        } else {
            if (y < 0) {
                direction = CLOCKWISE;
                t = y;
            } else {
                direction = COUNTERCLOCKWISE;
                t = y;
            }
        }
        driveTrainThread = new TurnDriveTrainThread(drivetrain, newAngle, direction, MaxSpeed, t);

    }

    @Override
    protected boolean isFinished() {
        return is_finished;
    }

    @Override
    public void execute() {
        if (!started) {
            started = true;
            driveTrainThread.start();
        }
        /*
         * Get current rotation using gyroscope
         * IF current rotation matches end rotation: isFinished = true AND return/skip
         * remaining code.
         * Set Motor speed respectively to rotate in place
         * 
         * 
         * 
         * 
         */
        // DriverStation.reportWarning(Double.toString(driveTrain.gyroscope.getAngle()),
        // false);
        // DriverStation.reportWarning(Double.toString(newAngle), false);
        /*
         * double currentAngle = driveTrain.gyroscope.getAngle();
         * double x = Util.normalize_angle_degrees(newAngle - currentAngle);
         * double y = x - 360;
         * double AbsX = Math.abs(x);
         * double AbsY = Math.abs(y);
         * 
         * 
         * if (AbsX<AbsY) {
         * if (x<0) {
         * if (CLOCKWISE!=direction) {
         * MaxSpeed-=0.015;
         * }
         * direction= CLOCKWISE;
         * t=x;
         * } else {
         * if (COUNTERCLOCKWISE!=direction) {
         * MaxSpeed-=0.015;
         * }
         * direction = COUNTERCLOCKWISE;
         * t=x;
         * }
         * } else {
         * if (y<0) {
         * if (CLOCKWISE!=direction) {
         * MaxSpeed-=0.015;
         * }
         * direction = CLOCKWISE;
         * t=y;
         * } else {
         * if (COUNTERCLOCKWISE!=direction) {
         * MaxSpeed-=0.015;
         * }
         * direction = COUNTERCLOCKWISE;
         * t=y;
         * }
         * }
         * if (currentAngle != newAngle) {
         * double speed = Math.max(0.01, MaxSpeed);
         * switch (direction) {
         * case CLOCKWISE:
         * driveTrain.leftMotor.set(-speed);
         * driveTrain.rightMotor.set(-speed);
         * break;
         * case COUNTERCLOCKWISE:
         * driveTrain.leftMotor.set(speed);
         * driveTrain.rightMotor.set(speed);
         * 
         * 
         * 
         * break;
         * }
         * }
         * if (currentAngle+5==newAngle && direction==CLOCKWISE) {
         * driveTrain.leftMotor.set(0);
         * driveTrain.rightMotor.set(0);
         * is_finished=true;
         * }
         * if (currentAngle-5==newAngle && direction==COUNTERCLOCKWISE) {
         * driveTrain.leftMotor.set(0);
         * driveTrain.rightMotor.set(0);
         * is_finished=true;
         * }
         */

        if (!driveTrainThread.isAlive()) {
            is_finished = true;
            scheduler.add(endCommand);
        }
    }

    // To Be Used When Choosing an Autonomous Command to help determine rotation on
    // the field
    private static void setStartingAngle(double angle) {
        startingAngle = angle;
    }
}
