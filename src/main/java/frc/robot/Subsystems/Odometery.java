package frc.robot.Subsystems;

import frc.robot.Constants;
import frc.robot.MathUtils.Vector2;

public class Odometery {
    private static Vector2 position;

    public static void init(){
        position = new Vector2(0, 0);
    }

    public static void update(){
        double leftDrive = Drivetrain.getLeftPercentOutput() * Constants.OdometeryConstants.PERCENT_TO_METERS;
        double rightDrive = Drivetrain.getRightPercentOutput() * Constants.OdometeryConstants.PERCENT_TO_METERS;
        
        double speed = (leftDrive + rightDrive)/2.0;

        Vector2 newMovment = new Vector2(speed, 0).rotate(Pigeon.getRotation());
        position = position.add(newMovment);
    }

    public static Vector2 getPosition(){
        return position;
    }
}
