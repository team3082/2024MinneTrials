package frc.robot;

import java.util.Optional;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.Odometry;
import frc.robot.Subsystems.Sensors.Pigeon;
import frc.robot.Subsystems.Sensors.Vision;

public class Telemetry {
    private static Field2d field;

    public static void init(){
        field = new Field2d();

        field.setRobotPose(new Pose2d());
        SmartDashboard.putData(field);
    }
    
    public static void update(){
        updateField();
    }

    private static void updateField(){
        field.setRobotPose(getPose2d());
        SmartDashboard.putData(field);
    }

    private static Pose2d getPose2d(){
        Rotation2d rotation = new Rotation2d(Pigeon.getRotation());

        Optional<Vector2> pos = Vision.getPosition();
        if(pos.isPresent())   return new Pose2d(new Translation2d(pos.get().x, pos.get().y), rotation);

        Vector2 position = Odometry.getPosition();
        Translation2d translation2d = new Translation2d(position.x, position.y);

        return new Pose2d(translation2d, rotation);
    }
}
