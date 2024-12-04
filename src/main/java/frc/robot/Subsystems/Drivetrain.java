package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;

/**
 * A class to manage the drivetrain subsystem, including motor initialization and 
 * control logic for driving the robot.
 */
public class Drivetrain {
    private static VictorSPX backRight;
    private static VictorSPX frontRight;
    private static VictorSPX backLeft;
    private static VictorSPX frontLeft;

    private static double leftSideOutput;
    private static double rightSideOutput;

    /**
     * Initializes the drivetrain motors and sets up following behavior for rear motors.
     * Rear motors follow their respective front motors to simplify control logic.
     */
    public static void init() {
        backLeft = new VictorSPX(Constants.DriveTrainConstants.BACK_LEFT_ID);
        frontLeft = new VictorSPX(Constants.DriveTrainConstants.FRONT_LEFT_ID);

        backRight = new VictorSPX(Constants.DriveTrainConstants.BACK_RIGHT_ID);
        frontRight = new VictorSPX(Constants.DriveTrainConstants.FRONT_RIGHT_ID);

        // Configure rear motors to follow front motors
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
    }

    /**
     * Implements arcade drive logic to control the robot's movement based on forward 
     * and rotational inputs.
     *
     * @param forward The desired forward/backward speed (-1.0 to 1.0).
     * @param rotate  The desired rotation speed (-1.0 to 1.0).
     */
    public static void arcadeDrive(double forward, double rotate) {
        // Calculate individual side speeds
        double leftSpeed = forward + rotate;
        double rightSpeed = forward - rotate;

        // Normalize speeds to stay within the range [-1, 1]
        if (Math.abs(leftSpeed) > 1) leftSpeed /= Math.abs(leftSpeed);
        if (Math.abs(rightSpeed) > 1) rightSpeed /= Math.abs(rightSpeed);

        // Update internal variables for telemetry or feedback
        leftSideOutput = leftSpeed;
        rightSideOutput = rightSpeed;

        // Set motor outputs
        frontLeft.set(ControlMode.PercentOutput, leftSpeed);
        frontRight.set(ControlMode.PercentOutput, rightSpeed);
    }

    /**
     * Gets the current output percentage of the left drivetrain side.
     *
     * @return The output percentage of the left side motors.
     */
    public static double getLeftPercentOutput() {
        return leftSideOutput;
    }

    /**
     * Gets the current output percentage of the right drivetrain side.
     *
     * @return The output percentage of the right side motors.
     */
    public static double getRightPercentOutput() {
        return rightSideOutput;
    }
}

 