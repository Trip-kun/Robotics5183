package frc.robot.control.command;

import frc.robot.control.RampCurve;
import frc.robot.control.tuple.AutonomousDoubleControl;
import frc.robot.control.tuple.DoubleControlStyle;
import frc.robot.hardware.gyro.SingleAxisGyroscope;
import frc.robot.subsystem.PhoenixDriveTrain;

public class Balance extends Command {
    PhoenixDriveTrain driveTrain;
    SingleAxisGyroscope gyro;
    DoubleControlStyle style;
    AutonomousDoubleControl dummy = new AutonomousDoubleControl(0, 0);
    static RampCurve curve = new RampCurve(RampCurve.Curve.Exponential, 100);
    public Balance(PhoenixDriveTrain driveTrain, SingleAxisGyroscope scope) {
        this.driveTrain=driveTrain;
        this.gyro=scope;
    }
    @Override
    public void run() {
        double pitch =gyro.getDegrees();
        dummy.updateValue(0, curve.curve(pitch/15));
        if (((int) (pitch)) == 0 ) {
            dummy.updateValue(0, 0);
        }
        driveTrain.autonomous(dummy);
    }

    @Override
    public void start() {
        style= driveTrain.controlStyle;
        driveTrain.controlStyle=dummy;
    }

    @Override
    public void clean() {
        driveTrain.controlStyle=style;
        driveTrain.autonomous(style);
    }

    @Override
    public boolean isFinished() {
        if ((int) gyro.getDegrees() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
