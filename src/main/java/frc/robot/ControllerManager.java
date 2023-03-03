package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class ControllerManager {
    private XboxController controller1;
    private XboxController controller2;
    public ControllerManager init() {
        controller1=new XboxController(RobotMap.ControllerNumber);
        controller2=new XboxController(RobotMap.Controller2Number);
        return this;
    }
    public XboxController getFirstController() {
        return controller1;
    }
    public XboxController getSecondController() {
        return controller2;
    }
}
