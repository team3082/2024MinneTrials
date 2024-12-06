package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;

public class Shooter {

    // motors
    private static TalonSRX intake;
    private static TalonSRX shooter;

    // starting time for ramping - shooter will ramp for 5 secs
    private static double startTime;

    private static ShooterState shooterState;

    private enum ShooterState{
        OFF,// both wheels off
        RAMPING, // flywheel speeding up
        IDLE, // flywheel at max speed
        SHOOTING, // move intake to shoot ball
        INTAKE,
    }

    public static ShooterState getShooterState() {
        return shooterState;
    }

    public static void init(){

        // initializes the motors, shooter is OFF when initialized
        intake = new TalonSRX(6);
        shooter = new TalonSRX(5);

        shooterState = ShooterState.OFF;

    }

    public static void ramp(){
        // starting time to count from, changes state to RAMPING
        startTime = Timer.getFPGATimestamp();
        shooterState = ShooterState.RAMPING;

    }

    public static void turnOff(){
        // turns shooter OFF
        shooterState = ShooterState.OFF;
    }

    // sets motor output for ramping
    public static void rampToSpeed(){
        shooter.set(TalonSRXControlMode.PercentOutput, 1);
        
    }


    public static void shoot(){
        // checks if motor output is at maximum
        if(shooterState == ShooterState.IDLE) {
            shooterState = ShooterState.SHOOTING;
        // if the motor is off instead, ramp
        } else if(shooterState == ShooterState.OFF) {
            ramp();
        }
        

    }

    public static void intakeOff() {
        intake.set(ControlMode.PercentOutput, 0);
    }

    // only spins intake motor
    public static void quickIntake() {
        intake.set(TalonSRXControlMode.PercentOutput, 0.5);
    }

    // sequence for shooting game pieces
    public static void update() {
        switch (shooterState) {
            case RAMPING:
                if(Timer.getFPGATimestamp() <= startTime + 1){
                    rampToSpeed();
                    break;
                }

                shooterState = ShooterState.IDLE;
            case IDLE:
                shooter.set(TalonSRXControlMode.PercentOutput, 0.75);
                break;
            case SHOOTING:
                intake.set(TalonSRXControlMode.PercentOutput, 0.75);
                shooter.set(TalonSRXControlMode.PercentOutput, 0.75);
                break;
            case OFF:
                intake.set(TalonSRXControlMode.PercentOutput, 0);
                shooter.set(TalonSRXControlMode.PercentOutput, 0);
                break;
            default:
                break;
        }
    }
}
