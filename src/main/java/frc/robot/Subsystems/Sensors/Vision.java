package frc.robot.Subsystems.Sensors;

import java.util.Optional;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.MathUtils.Vector2;

public class Vision {
    public void init(){
           
    }

    public static Optional<Vector2> getPosition(){
        
        LimelightHelpers.PoseEstimate poseEstimate = LimelightHelpers.getBotPoseEstimate_wpiBlue("limelight");
        
        boolean doRejectUpdate = false;

        if(poseEstimate.tagCount == 1 && poseEstimate.rawFiducials.length == 1){
            if(poseEstimate.rawFiducials[0].ambiguity > .7){
                doRejectUpdate = true;
            }
            if(poseEstimate.rawFiducials[0].distToCamera > 3){
                doRejectUpdate = true;
            }
        }

        if(poseEstimate.tagCount == 0){
            doRejectUpdate = true;
        }

        Pose2d pose = poseEstimate.pose;

        if(!doRejectUpdate) return Optional.of(new Vector2(pose.getX(), pose.getY()));
        return Optional.empty();
    } 


}
