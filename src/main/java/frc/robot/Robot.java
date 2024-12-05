// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Odometry;
import frc.robot.Subsystems.Sensors.Pigeon;

public class Robot extends TimedRobot {
  @Override
  public void robotInit() {
    Pigeon.init();
    Drivetrain.init(); 
    Odometry.init();
    Telemetry.init();
    OI.init();
  }
  
  @Override
  public void robotPeriodic() {
    Pigeon.update();
    Odometry.update();
    Telemetry.update();
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    Drivetrain.arcadeDrive(OI.getForward(), OI.getRotation());
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
