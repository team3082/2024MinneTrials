package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Subsystems.Shooter;


public class OI {
    private static Joystick joystick;
    private static int shooterButton = 2;
    private static int intakeButton = 5;

    public static void init(){
        joystick = new Joystick(Constants.OIConstants.CONTROLLER_PORT);
    }

    public static double getForward(){
        double output = joystick.getRawAxis(Constants.OIConstants.DRIVE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return -output;
        }
        return 0.00;

    }

    public static double getRotation(){
        double output = joystick.getRawAxis(Constants.OIConstants.ROTATE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return output*.3;
        }
        return 0.000;
    }

    public static void getShooterInput() {
        // is the left shoulder being pushed down?
        if(joystick.getRawButtonPressed(intakeButton)){
            // spin intake motor
            Shooter.quickIntake();
        }

        if(joystick.getRawButtonReleased(intakeButton)){
            Shooter.intakeOff();
        }

        // is the left trigger pushed down all the way?
        if(joystick.getRawAxis(shooterButton) > 0.95){
            Shooter.shoot();
        } else {
            Shooter.turnOff();
        }

        // prints the state that the shooter's state machine is in at the time
        System.out.println(Shooter.getShooterState());
        
    }
}
 

