package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;


public class OI {
    private static Joystick joystick;
 

    public static void init(){
        joystick = new Joystick(Constants.OIConstants.CONTROLLER_PORT);
    }

    public static double getForward(){
        double output = joystick.getRawAxis(Constants.OIConstants.DRIVE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return -output;
        }
        return 0;

    }

    public static double getRotation(){
        double output = joystick.getRawAxis(Constants.OIConstants.ROTATE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return output*.3;
        }
        return 0;
    }

    public static void update() {
        Drivetrain.arcadeDrive(OI.getRotation(), OI.getForward());
        
        if(joystick.getRawAxis(Constants.OIConstants.SHOOTER_BUTTON) > 0.95){
            Shooter.shoot();
        }

        if(joystick.getRawButton(Constants.OIConstants.INTAKE_BUTTON)){
            Intake.manualIntake();
        } 
        
        if(joystick.getRawButtonReleased(Constants.OIConstants.INTAKE_BUTTON)){
            Intake.manualOff();
        }

    }

    public static boolean getShootWhenRunning() {
        if(joystick.getRawAxis(Constants.OIConstants.SHOOTER_BUTTON) > 0.95) return true;
        return false;
    }
}
 

