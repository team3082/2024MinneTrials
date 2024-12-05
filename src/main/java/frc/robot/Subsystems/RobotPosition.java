package frc.robot.Subsystems;

import java.util.Optional;

import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.Sensors.Pigeon;
import frc.robot.Subsystems.Sensors.Vision;

/**
 * A class to handle the robot position, tracking the robot's position on the field 
 * by combining data from vision and odometry
 */
public class RobotPosition {
    private Vector2 position;
    private double VISION_CORRECTION_FACTOR = .7;

    public void init(){
        position = new Vector2();
    }

    public void update(){
        position.add(Odometry.getPositionChange());
        
        Optional<Vector2> visionPosition = Vision.getPosition();

        if(visionPosition.isPresent()){
            Vector2 positionError = visionPosition.get().sub(position);
            position = position.add(positionError.mul(VISION_CORRECTION_FACTOR));
        }
    }

    public Vector2 getPostion(){
        return position;
        
    }

}
