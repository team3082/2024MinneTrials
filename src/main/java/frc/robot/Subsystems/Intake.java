package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/**
 * The Intake class manages the intake motor's states and actions for the robot.
 * It controls the manual intake and shooter-related intake behaviors.
 */
public class Intake {

    private static TalonSRX intakeMotor;

    /**
     * Enum representing the possible states of the intake.
     */
    private enum IntakeState {
        OFF,           // Intake is off
        COASTING,      // Coasting to allow shooter to put the ball in
        MANUAL_INTAKE, // Manual intake, operator controls intake motor
        SHOOTER_INTAKE // Intake motor moves for shooting action
    }

    private static IntakeState intakeState;
    private static double coastStartTime;

    /**
     * Retrieves the current state of the intake.
     * 
     * @return The current intake state.
     */
    public static IntakeState getIntakeState() {
        return intakeState;
    }

    /**
     * Initializes the intake system by setting up the intake motor and default state.
     */
    public static void init() {
        intakeMotor = new TalonSRX(Constants.IntakeConstants.INTAKE_MOTOR_ID); 
        intakeMotor.setNeutralMode(NeutralMode.Brake);
        intakeState = IntakeState.OFF;
        SmartDashboard.putString("Intake State", intakeState.name());
    }

    /**
     * Turns off the intake motor unless the intake state is SHOOTER_INTAKE.
     * This method is used to stop manual intake or shooter intake processes.
     */
    public static void manualOff() {
        if (intakeState == IntakeState.SHOOTER_INTAKE) return;

        intakeState = IntakeState.COASTING;
        intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
        intakeMotor.setNeutralMode(NeutralMode.Coast);
        Shooter.intake();
        coastStartTime = Timer.getFPGATimestamp();
        SmartDashboard.putString("Intake State", intakeState.name());
    }

    /**
     * Forces the intake motor to turn off regardless of the current state.
     * This method ensures the intake is off immediately.
     */
    public static void shooterOff() {
        intakeState = IntakeState.OFF;
        intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
        SmartDashboard.putString("Intake State", intakeState.name());
    }

    /**
     * Sets the intake to manual control, where the operator can adjust the intake motor speed.
     */
    public static void manualIntake() {
        if(intakeState == IntakeState.SHOOTER_INTAKE) return;

        intakeState = IntakeState.MANUAL_INTAKE;
        intakeMotor.set(TalonSRXControlMode.PercentOutput, Constants.IntakeConstants.MANUAL_INTAKE_SPEED); 
        SmartDashboard.putString("Intake State", intakeState.name());
    }

    /**
     * Forces the intake motor to run for the shooter intake process.
     * This is used when preparing to shoot or manipulate a ball.
     */
    public static void shooterIntake() {
        intakeState = IntakeState.SHOOTER_INTAKE;
        intakeMotor.set(TalonSRXControlMode.PercentOutput, Constants.IntakeConstants.SHOOT_INTAKE_SPEED); 
        SmartDashboard.putString("Intake State", intakeState.name());
    }

    /**
     * Periodically updates the intake subsystem, performing actions based on the current intake state.
     * This method is called during each robot cycle to ensure the intake behaves correctly.
     */
    public static void update() {
        switch (intakeState) {
            case OFF:
                intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
                break;
            case COASTING:
                intakeMotor.set(TalonSRXControlMode.PercentOutput, 0);
                if (coastStartTime+Constants.IntakeConstants.COAST_TIME < Timer.getFPGATimestamp()){
                    intakeMotor.setNeutralMode(NeutralMode.Brake);
                    intakeState = IntakeState.OFF;
                    Shooter.intakeOff();
                }
                break;
            case MANUAL_INTAKE:
                intakeMotor.set(TalonSRXControlMode.PercentOutput, Constants.IntakeConstants.MANUAL_INTAKE_SPEED); 
                break;
            case SHOOTER_INTAKE:
                intakeMotor.set(TalonSRXControlMode.PercentOutput, Constants.IntakeConstants.SHOOT_INTAKE_SPEED); 
                break;
            default:
                break;
        }

        SmartDashboard.putString("Intake State", intakeState.name());
    }
}
