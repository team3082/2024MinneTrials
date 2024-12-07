package frc.robot;

/**
 * A container for all robot-wide constants. Constants are organized by subsystem or functionality.
 * These constants are used throughout the robot's code to configure and control different subsystems.
 */
public class Constants {

    /**
     * Constants related to the drivetrain subsystem.
     * These constants define the CAN IDs for the motors in the drivetrain.
     */
    public static class DriveTrainConstants {
        public static final int BACK_RIGHT_ID = 1;        // CAN ID for the back right motor
        public static final int BACK_LEFT_ID = 4;         // CAN ID for the back left motor
        public static final int FRONT_RIGHT_ID = 2;       // CAN ID for the front right motor
        public static final int FRONT_LEFT_ID = 3;        // CAN ID for the front left motor
    }

    /**
     * Constants related to operator input (OI).
     * These constants are used for joystick button mappings and axis configurations for controlling the robot.
     */
    public static class OIConstants {
        public static final int CONTROLLER_PORT = 0;       // Port for the primary controller
        public static final int DRIVE_AXIS = 1;            // Axis for forward/backward movement
        public static final int ROTATE_AXIS = 4;           // Axis for rotation
        public static final double RANGE = 0.16;           // Deadzone range for joystick input
        public static final int SHOOTER_BUTTON = 2;        // Button for the shooter
        public static final int INTAKE_BUTTON = 5;         // Button for manual intake
    }

    /**
     * Constants related to the Shooter subsystem.
     * These constants are used to configure the shooter mechanism's behavior.
     */
    public static class ShooterConstants {
        public static final double targetVelocity = -1;    // Target velocity for the shooter (could be adjusted for shooting speed)
        public static final double RAMP_TIME = 1;          // Time to ramp up the shooter motor (in seconds)
        public static final double SHOOT_TIME = 1;           // Time for the shooter to shoot (in seconds)
    }

    /**
     * Constants related to the Intake subsystem.
     * These constants control the speed of the intake motor during different operations.
     */
    public static class IntakeConstants {
        public static final double SHOOT_INTAKE_SPEED = 1;    // Speed of intake motor during shooter intake
        public static final double MANUAL_INTAKE_SPEED = 0.5; // Speed of intake motor during manual intake
        public static final int INTAKE_MOTOR_ID = 6;          // CAN ID for the intake motor
        public static final double COAST_TIME = .5;           // The amount of time to coast
        public static final double SHOOTER_BACK_SPEED = -0.1; // Speed of shooter to put 
    }

    /**
     * Constants related to Autonomous mode.
     * These constants define the behavior of the robot during autonomous operations.
     */
    public static class AutoConstants {
        public static final double AUTO_DURATION = 2;          // Duration of the autonomous movement (in seconds)
        public static final double AUTO_SPEED = 0.5;           // Speed of the robot during autonomous (scaled from 0 to 1)
    } 
}
