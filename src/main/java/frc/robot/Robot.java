package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;

/**
 * Main Robot class extending TimedRobot to handle periodic robot operations.
 */
public class Robot extends TimedRobot {
  private double startTime; // Records the start time for autonomous mode

  /**
   * Initializes subsystems and operator interface when the robot is powered on or reset.
   */
  @Override
  public void robotInit() {
    Drivetrain.init(); // Initialize Drivetrain
    OI.init();         // Initialize Operator Interface
    Intake.init();     // Initialize Intake
    Shooter.init();    // Initialize Shooter
  }

  /**
   * Periodically updates the subsystems during the robot's enabled state.
   */
  @Override
  public void robotPeriodic() {
    Shooter.update(); // Update Shooter state
    Intake.update();  // Update Intake state
  }

  /**
   * Records the start time for autonomous mode.
   */
  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  /**
   * Controls robot movement during autonomous mode based on elapsed time.
   */
  @Override
  public void autonomousPeriodic() {
    if (startTime + Constants.AutoConstants.AUTO_DURATION > Timer.getFPGATimestamp()) {
      Drivetrain.arcadeDrive(0, Constants.AutoConstants.AUTO_SPEED); // Drive forward
    } else {
      Drivetrain.arcadeDrive(0, 0); // Stop robot after the set duration
    }
  }

  /**
   * Updates the operator interface during teleoperated mode to control the robot.
   */
  @Override
  public void teleopPeriodic() {
    OI.update(); // Process operator input to control the robot
  }
}
