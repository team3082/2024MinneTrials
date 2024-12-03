package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;

public class Drivetrain {
    private static VictorSPX backRight;
    private static VictorSPX frontRight;
    private static VictorSPX backLeft;
    private static VictorSPX frontLeft;

    private static double leftSideOutput;
    private static double rightSideOutput;

    public static void init(){
        backLeft = new VictorSPX(Constants.DriveTrainConstants.BACK_LEFT_ID);
        frontLeft = new VictorSPX(Constants.DriveTrainConstants.FRONT_LEFT_ID);

        backRight = new VictorSPX(Constants.DriveTrainConstants.BACK_RIGHT_ID);
        frontRight = new VictorSPX(Constants.DriveTrainConstants.FRONT_RIGHT_ID);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
    }

    public static void arcadeDrive(double rotate, double drive) {
        double max= Math.max(Math.abs(rotate), Math.abs(drive)); 
        double total = drive + rotate;
        double difference = drive - rotate;

        double rightOutput = 0;
        double leftOutput = 0;

        // Constraining by quadrant
        // Joystick (x,y) -> Motor output [L, R] (in terms of percent output)
        if (drive >= 0) { // Q1 and Q2
            if (rotate >= 0) { // Q1
                // (1, 1) -> [1, 0]
                leftOutput = (max);
                rightOutput = (difference);
            } else { // Q2
                // (-1, 1) -> [0, 1]
                leftOutput = (total);
                rightOutput = (max);
            }
        } else { // Q3 and Q4
            if (rotate >= 0) { // Q4
                // (1,-1) -> [-1, 0]
                rightOutput = (total);
                leftOutput = (-max);
            } else { // Q3
                // (-1,-1) -> [0, -1]
                rightOutput = (-max);
                leftOutput = (difference);
            }
        }

        frontLeft.set(ControlMode.PercentOutput, leftOutput);
        frontRight.set(ControlMode.PercentOutput, rightOutput);

        rightSideOutput = rightOutput;
        leftSideOutput = leftOutput;
    }

    public static double getLeftPercentOutput(){
        return leftSideOutput;
    }
    
    public static double getRightPercentOutput(){
        return rightSideOutput;
    }
}
 