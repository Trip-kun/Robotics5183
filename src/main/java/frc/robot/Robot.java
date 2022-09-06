// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.MoveDriveTrainByDistance;
import frc.robot.subsystem.*;
import frc.robot.subsystem.hardware.SPIGyroscope;
import frc.robot.subsystem.hardware.SparkMotor;
import frc.robot.subsystem.hardware.TalonFXMotor;

/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    public Scheduler scheduler = Scheduler.getInstance();
    DriveTrain driveTrain;
    LiftSpool liftSpool;
    ShooterRotator shooterRotator;
    Shooter shooter;
    ControllerManager controllerManager=new ControllerManager();
    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        controllerManager.init();
        driveTrain = new DriveTrain(new TalonFXMotor(RobotMap.UpperLeftMotor), new TalonFXMotor(RobotMap.UpperRightMotor), new TalonFXMotor(RobotMap.LowerLeftMotor), new TalonFXMotor(RobotMap.LowerRightMotor), controllerManager.getFirstController(), new SPIGyroscope(new ADXRS450_Gyro()));
        liftSpool = new LiftSpool(new SparkMotor(RobotMap.spoolMotor), controllerManager.getSecondController());
        shooterRotator = new ShooterRotator(new SparkMotor(RobotMap.rotatorMotor), controllerManager.getSecondController());
        shooter=new Shooter(new SparkMotor(RobotMap.leftShooterMotor),new SparkMotor( RobotMap.rightShooterMotor), controllerManager.getSecondController());
        driveTrain.gyroscope.calibrate();


    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     * <p>
     * This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        driveTrain.periodic();
        liftSpool.periodic();
        shooterRotator.periodic();
        shooter.periodic();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different
     * autonomous modes using the dashboard. The sendable chooser code works with the Java
     * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
     * uncomment the getString line to get the auto name from the text box below the Gyro.
     * <p>
     * You can add additional auto modes by adding additional comparisons to the switch structure
     * below with additional strings. If using the SendableChooser make sure to add them to the
     * chooser code above as well.
     */
    @Override
    public void autonomousInit()
    {

        //Command command2 = new TurnDriveTrain(TurnModes.RELATIVE, -45, driveTrain, 0.25, new InstantCommand());          ;
        //scheduler.add(new TurnDriveTrain(TurnModes.RELATIVE, 270, driveTrain, 0.25, command2));
        scheduler.add(new MoveDriveTrainByDistance(0.5, 12*3, true, driveTrain, new InstantCommand()));
    }

    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic()
    {
        scheduler.run();
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {}

    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        driveTrain.ArcadeDrive(true);
        liftSpool.Drive();
        shooterRotator.teleop();
        shooter.Drive();
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {}

    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}


