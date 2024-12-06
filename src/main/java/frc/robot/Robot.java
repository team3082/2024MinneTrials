// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Odometry;
import frc.robot.Subsystems.Shooter;
import frc.robot.Subsystems.Sensors.Pigeon;
import frc.robot.Subsystems.Sensors.Vision;

public class Robot extends TimedRobot {
  private double startTime = 0;
  private double timeLength = 0;
  @Override
  public void robotInit() {
    // Pigeon.init();
    Drivetrain.init(); 
    //Odometry.init();
    // Telemetry.init();
    OI.init();
    Shooter.init();
  }
  
  @Override
  public void robotPeriodic() {
    // Pigeon.update();
    //Odometry.update();
    // Telemetry.update();
    Shooter.update();
  }

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    if(startTime + 2 > Timer.getFPGATimestamp()){
      Drivetrain.arcadeDrive(0, .5);
    } else {
      Drivetrain.arcadeDrive(0, 0);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    /*Optional<Vector2> vec = Vision.getPosition();
    if(vec.isPresent())
      System.out.println(vec.get());*/
    Drivetrain.arcadeDrive(OI.getRotation(), OI.getForward());
    OI.getShooterInput();
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
