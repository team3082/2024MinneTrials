package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.OI;

/**
 * The Shooter class manages the shooter motor's states and actions for the robot.
 * It controls the ramping process, shooting behavior, and manages state transitions.
 */
public class Shooter {

    private static TalonSRX shooterMotor;
    private static double rampStartTime;
    private static double shootStartTime;

    /**
     * Enum representing the possible states of the shooter.
     */
    private enum ShooterState {
        INTAKING,   //Shooter is moving backwards to intake
        OFF,       // Shooter is off
        RAMPING,   // Shooter is ramping up to speed
        SHOOTING   // Shooter is actively shooting
    }

    private static ShooterState shooterState;

    /**
     * Initializes the shooter subsystem, including motor setup and initial state.
     */
    public static void init() {
        shooterMotor = new TalonSRX(5);  // Initializes the motor on CAN ID 5
        shooterState = ShooterState.OFF;  // Set the initial state to OFF
        SmartDashboard.putString("Shooter State", shooterState.name()); // Display state on the dashboard
    }

    /**
     * Returns the current state of the shooter.
     * 
     * @return The current shooter state.
     */
    public static ShooterState getShooterState() {
        return shooterState;
    }

    /**
     * Initiates the shooting process by starting the ramping stage.
     */
    public static void shoot() {
        if (shooterState != ShooterState.OFF) return;

        rampStartTime = Timer.getFPGATimestamp();
        shooterState = ShooterState.RAMPING;
        shooterMotor.set(TalonSRXControlMode.PercentOutput, 1);

        SmartDashboard.putString("Shooter State", shooterState.name());
    }

    /**
     * Turns off the shooter motor if intaking
     */
    public static void intakeOff() {
        if(shooterState != ShooterState.INTAKING) return;
        shooterMotor.set(TalonSRXControlMode.PercentOutput, 0);
        shooterState = ShooterState.OFF;
        SmartDashboard.putString("Shooter State", shooterState.name());
    }

    /**
     * Handles the ramping behavior of the shooter motor. 
     * Once ramping is complete, transitions to the SHOOTING state.
     */
    private static void ramping() {
        if (rampStartTime + Constants.ShooterConstants.RAMP_TIME < Timer.getFPGATimestamp()) {
            Intake.shooterIntake();
            shooterState = ShooterState.SHOOTING;
            shootStartTime = Timer.getFPGATimestamp();
            SmartDashboard.putString("Shooter State", shooterState.name());
        }

        shooterMotor.set(TalonSRXControlMode.PercentOutput, 1);
    }

    /**
     * Handles the shooting behavior of the shooter motor.
     * Transitions back to OFF state once shooting time has passed.
     * 
     * @see OI#getShootWhenRunning() for shoot control.
     */
    private static void shooting() {
        if (shootStartTime + Constants.ShooterConstants.SHOOT_TIME < Timer.getFPGATimestamp() && !OI.getShootWhenRunning()) {
            Intake.shooterOff();
            shooterState = ShooterState.OFF;
            shooterMotor.set(TalonSRXControlMode.PercentOutput, 0);
            SmartDashboard.putString("Shooter State", shooterState.name());
        }
    }

    public static void intake(){
        shooterMotor.set(TalonSRXControlMode.PercentOutput, Constants.IntakeConstants.SHOOTER_BACK_SPEED);
        shooterState = ShooterState.INTAKING;
        SmartDashboard.putString("Shooter State", shooterState.name());
    }

    /**
     * Periodically updates the shooter subsystem, performing actions based on the current shooter state.
     */
    public static void update() {
        switch (shooterState) {
            case INTAKING:
                shooterMotor.set(TalonSRXControlMode.PercentOutput, Constants.IntakeConstants.SHOOTER_BACK_SPEED);
            case OFF:
                shooterMotor.set(TalonSRXControlMode.PercentOutput, 0);
                break;
            case RAMPING:
                ramping();
                break;
            case SHOOTING:
                shooting();
                break;
            default:
                break;
        }

        SmartDashboard.putString("Shooter State", shooterState.name());
    }
}
