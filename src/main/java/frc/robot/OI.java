package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;

/**
 * The OI (Operator Interface) class handles joystick input and translates 
 * it into actions for controlling the robot's subsystems.
 */
public class OI {
    private static Joystick joystick;

    /**
     * Initializes the joystick used for controlling the robot.
     */
    public static void init(){
        joystick = new Joystick(Constants.OIConstants.CONTROLLER_PORT);
    }

    /**
     * Retrieves the forward movement input from the joystick.
     * Adjusts for deadzone and inverts the value for correct forward direction.
     * 
     * @return The forward movement value, or 0 if within the deadzone.
     */
    public static double getForward(){
        double output = joystick.getRawAxis(Constants.OIConstants.DRIVE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return -output;  // Invert the joystick value for correct forward movement
        }
        return 0;
    }

    /**
     * Retrieves the rotation input from the joystick, scaled for better control.
     * 
     * @return The rotation value, or 0 if within the deadzone.
     */
    public static double getRotation(){
        double output = joystick.getRawAxis(Constants.OIConstants.ROTATE_AXIS);
        if(Math.abs(output) > Constants.OIConstants.RANGE){
            return output * 0.3;  // Apply scaling for finer control
        }
        return 0;
    }

    /**
     * Updates the robot's subsystems based on joystick input.
     * Controls the drivetrain, shooter, and intake actions.
     */
    public static void update() {
        Drivetrain.arcadeDrive(OI.getRotation(), OI.getForward());

        // Trigger shooter action if the shooter button is pressed
        if(joystick.getRawAxis(Constants.OIConstants.SHOOTER_BUTTON) > 0.95){
            Shooter.shoot();
        }

        // Enable manual intake if the intake button is pressed
        if(joystick.getRawButton(Constants.OIConstants.INTAKE_BUTTON)){
            Intake.manualIntake();
        }

        // Disable manual intake if the intake button is released
        if(joystick.getRawButtonReleased(Constants.OIConstants.INTAKE_BUTTON)){
            Intake.manualOff();
        }
    }

    /**
     * Checks if the shooter button is pressed, indicating that shooting 
     * should occur while moving.
     * 
     * @return True if the shooter button is pressed, false otherwise.
     */
    public static boolean getShootWhenRunning() {
        return joystick.getRawAxis(Constants.OIConstants.SHOOTER_BUTTON) > 0.95;
    }
}
