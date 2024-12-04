package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.Odometry;
import frc.robot.Subsystems.Sensors.Pigeon;

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
        Vector2 position = Odometry.getPosition();
        Translation2d translation2d = new Translation2d(position.x, position.y);

        Rotation2d rotation = new Rotation2d(Pigeon.getRotation());

        return new Pose2d(translation2d, rotation);
    }
}
