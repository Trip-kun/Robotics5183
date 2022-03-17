package frc.robot.subsystem;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystem.hardware.SparkMotor;

public class LiftSpool extends Subsystem {
    private SparkMotor spoolMotor;
    private XboxController xbox;

    public LiftSpool(SparkMotor spoolMotor, XboxController xboxController) {
        this.spoolMotor = spoolMotor;
        xbox = xboxController;
        this.spoolMotor.setInverted(false);
        this.spoolMotor.setSafety(true);
    }

    public void Drive() {
        double speed = 0;
        if (xbox.getPOV() == 0) {
            speed = 1;
        }
        if (xbox.getPOV() == 180) {
            speed = -1;
        }
        this.spoolMotor.set(speed);

    }

    public void periodic() {
        spoolMotor.periodic();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
