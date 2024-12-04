package frc.robot.Subsystems;

import frc.robot.Constants;
import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.Sensors.Pigeon;

/**
 * A class to handle the odometry system, tracking the robot's position on the field 
 * using drivetrain outputs and gyroscopic rotation data.
 */
public class Odometry {
    private static Vector2 position;
    private static Vector2 positionChange;

    /**
     * Initializes the odometry system by resetting the robot's position to the origin (0, 0).
     */
    public static void init() {
        position = new Vector2(0, 0);
    }

    /**
     * Updates the robot's position based on drivetrain outputs and current rotation.
     * Calculates movement in the robot's local coordinate frame and rotates it to the 
     * field coordinate frame.
     */
    public static void update() {
        // Convert drivetrain outputs to approximate distances traveled in meters
        double leftDrive = Drivetrain.getLeftPercentOutput() * Constants.OdometryConstants.PERCENT_TO_METERS;
        double rightDrive = Drivetrain.getRightPercentOutput() * Constants.OdometryConstants.PERCENT_TO_METERS;

        // Average the left and right drivetrain distances to estimate forward speed
        double speed = (leftDrive + rightDrive) / 2.0;

        // Create a vector representing movement in the robot's local frame (forward direction)
        positionChange = new Vector2(speed, 0).rotate(Pigeon.getRotation());

        // Add the rotated movement vector to the current position to update the robot's field position
        position = position.add(positionChange);
    }

    /**
     * Gets the current position of the robot on the field.
     *
     * @return A {@code Vector2} representing the robot's position in meters.
     */
    public static Vector2 getPosition() {
        return position;
    }

     /**
     * Gets the change in position of the robot on the field.
     *
     * @return A {@code Vector2} representing the robot's position in meters.
     */
    public static Vector2 getPositionChange() {
        return positionChange;
    }
}
