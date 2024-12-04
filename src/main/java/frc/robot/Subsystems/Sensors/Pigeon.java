package frc.robot.Subsystems.Sensors;

import com.ctre.phoenix6.hardware.Pigeon2;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Subsystems.Drivetrain;

/**
 * A class to handle the integration of a Pigeon IMU (Inertial Measurement Unit)
 * with both real hardware and simulation environments.
 */
public class Pigeon {
    private static Pigeon2 pigeon;
    private static double simulatedRotation;

    /**
     * Initializes the Pigeon IMU hardware or sets up for simulation.
     */
    public static void init() {
        pigeon = new Pigeon2(Constants.PigeonConstants.PIGEON_PORT);
        simulatedRotation = 0;
    }

    /**
     * Updates the simulated rotation based on the drivetrain's output when
     * running in a simulation environment.
     */
    public static void update() {
        if (Robot.isReal()) return;

        double leftDrive = Drivetrain.getLeftPercentOutput();
        double rightDrive = Drivetrain.getRightPercentOutput();

        double rotationChange = (rightDrive - leftDrive) / (2 * Constants.OdometryConstants.DRIVE_WIDTH);
        simulatedRotation = addAngle(simulatedRotation, rotationChange);
    }

    /**
     * Gets the current rotation of the robot in radians.
     *
     * @return The robot's yaw angle in radians. Uses the Pigeon hardware in a real
     * environment and the simulated rotation otherwise.
     */
    public static double getRotation() {
        if (Robot.isReal()) {
            return normalizeAngle(pigeon.getYaw().getValueAsDouble());
        }

        return simulatedRotation;
    }

    /**
     * Resets the Pigeon IMU yaw angle to zero.
     */
    public static void zero() {
        pigeon.setYaw(0);
    }

    /**
     * Sets the yaw angle of the robot.
     *
     * @param radians The desired yaw angle in radians.
     */
    public static void setYaw(double radians) {
        pigeon.setYaw(radians * 180 / Math.PI);
        simulatedRotation = radians;
    }

    /**
     * Normalizes an angle to the range [-π, π].
     *
     * @param angleDegrees The angle in degrees.
     * @return The normalized angle in radians.
     */
    private static double normalizeAngle(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        angleRadians = Math.atan2(Math.sin(angleRadians), Math.cos(angleRadians));

        return angleRadians;
    }

    /**
     * Adds two angles in radians, normalizing the result to the range [-π, π].
     *
     * @param rad1 The first angle in radians.
     * @param rad2 The second angle in radians.
     * @return The sum of the angles, normalized to the range [-π, π].
     */
    private static double addAngle(double rad1, double rad2) {
        double sum = rad1 + rad2;
        return Math.atan2(Math.sin(sum), Math.cos(sum));
    }
}
