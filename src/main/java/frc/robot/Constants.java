package frc.robot;

/**
 * A container for all robot-wide constants. Constants are organized by subsystem or functionality.
 */
public class Constants {

    /**
     * Constants related to the drivetrain subsystem.
     */
    public static class DriveTrainConstants {
        public static final int BACK_RIGHT_ID = 0; // CAN ID for the back right motor
        public static final int BACK_LEFT_ID = 0;  // CAN ID for the back left motor
        public static final int FRONT_RIGHT_ID = 0; // CAN ID for the front right motor
        public static final int FRONT_LEFT_ID = 0;  // CAN ID for the front left motor
    }

    /**
     * Constants related to the odometry system.
     */
    public static class OdometryConstants {
        public static final double PERCENT_TO_METERS = 0.1; // Conversion factor from percent output to meters
        public static final int DRIVE_WIDTH = 1;           // Distance between left and right drive sides in meters
    }

    /**
     * Constants related to operator input (OI).
     */
    public static class OIConstants {
        public static final int CONTROLLER_PORT = 0;  // Port for the primary controller
        public static final int DRIVE_AXIS = 1;       // Axis for forward/backward movement
        public static final int ROTATE_AXIS = 2;      // Axis for rotation
        public static final double RANGE = 0.16;     // Deadzone range for joystick input
    }

    /**
     * Constants related to the Pigeon gyroscope sensor.
     */
    public static class PigeonConstants {
        public static final int PIGEON_PORT = 1; // CAN ID for the Pigeon gyroscope
    }

    /**
     * Constants related to the Shooter
     */
    public static class ShooterConstants{
        public static final double targetVelocity = -1;
    }
}

