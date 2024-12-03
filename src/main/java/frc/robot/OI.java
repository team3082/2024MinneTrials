package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    private static Joystick joystick;

    public static void init(){
        joystick = new Joystick(Constants.OIConstants.CONTROLLER_PORT);
    }

    public static double getForward(){
        double output = joystick.getRawAxis(Constants.OIConstants.DRIVE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return output;
        }
        return 0.00;

    }

    public static double getRotation(){
        double output = joystick.getRawAxis(Constants.OIConstants.ROTATE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return output;
        }
        return 0.000;
    }
}
 

