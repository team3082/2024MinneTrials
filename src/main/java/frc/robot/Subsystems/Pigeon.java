package frc.robot.Subsystems;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;

import frc.robot.Constants;
import frc.robot.Robot;

public class Pigeon {
    private static double simRotation;
    private static WPI_PigeonIMU pigeon;

    public static void init(){
        pigeon = new WPI_PigeonIMU(Constants.PigeonConstants.PIGEON_PORT);
        simRotation = 0;
    }

    public static void update(){
        if(Robot.isReal()) return;

        double leftDrive = Drivetrain.getLeftPercentOutput();
        double rightDrive = Drivetrain.getRightPercentOutput();
        
        simRotation = addAngle(simRotation, (rightDrive-leftDrive)/2*Constants.OdometeryConstants.DRIVE_WIDTH);
    }

    public static double getRotation(){
        if(Robot.isReal()){
            return pigeon.getAngle();
        } 
        
        return simRotation;
    }

    private static double addAngle(double rad1, double rad2) {
        double sum = rad1 + rad2;
        return Math.atan2(Math.sin(sum), Math.cos(sum));
    }
}
