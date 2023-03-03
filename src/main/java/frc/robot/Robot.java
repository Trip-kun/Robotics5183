// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.control.Scheduler;
import frc.robot.control.command.SetClaw;
import frc.robot.control.command.WaitCommand;
import frc.robot.control.tuple.CombinedDouble;
import frc.robot.hardware.encoder.CANEncoder;
import frc.robot.hardware.encoder.TalonFXEncoder;
import frc.robot.hardware.motor.TalonFXMotor;
import frc.robot.hardware.pneumatic.DoubleSolenoid;
import frc.robot.hardware.pneumatic.Solenoid;
import frc.robot.subsystem.*;
import frc.robot.hardware.SPIGyroscope;
import frc.robot.hardware.motor.VictorSPXMotor;

/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    public PhoenixDriveTrain driveTrain;
    public PneumaticBase pneumaticBase;
    public ControllerManager controllerManager=RobotMap.controllerManager;
    public Arm arm;
    public Claw claw;
    public Scheduler scheduler;
    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        PneumaticsBase airBase = new PneumaticsControlModule();
        driveTrain = new PhoenixDriveTrain(new VictorSPXMotor(RobotMap.UpperLeftMotor), new VictorSPXMotor(RobotMap.UpperRightMotor), new VictorSPXMotor(RobotMap.LowerLeftMotor), new VictorSPXMotor(RobotMap.LowerRightMotor), controllerManager.getFirstController(), new SPIGyroscope(new ADXRS450_Gyro()), RobotMap.driveTrainControl, new CANEncoder(0));
        driveTrain.gyroscope.calibrate();
        pneumaticBase=new PneumaticBase(airBase, RobotMap.compressorControl);
        TalonFXMotor armMotor = new TalonFXMotor(RobotMap.armMotor);
        arm = new Arm(armMotor, new DoubleSolenoid(airBase.makeDoubleSolenoid(0, 1)), RobotMap.armControl, new TalonFXEncoder(armMotor.getRawMasterMotor()));
        claw = new Claw(new Solenoid(airBase.makeSolenoid(2)), RobotMap.clawControl);
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
        scheduler.scheduleCommand(new SetClaw(claw, true));
        scheduler.scheduleCommand(new WaitCommand(5, claw));
        scheduler.scheduleCommand(new SetClaw(claw, false));
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
        arm.teleop();

        driveTrain.ArcadeDrive(true);
        pneumaticBase.teleop();
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


