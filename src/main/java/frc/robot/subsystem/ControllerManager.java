package frc.robot.subsystem;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap;

public class ControllerManager {
    private XboxController controller1;
    private XboxController controller2;
    public void init() {
        controller1=new XboxController(RobotMap.ControllerNumber);
        controller2=new XboxController(RobotMap.Controller2Number);
    }
    public XboxController getFirstController() {
        return controller1;
    }
    public XboxController getSecondController() {
        return controller2;
    }
}
