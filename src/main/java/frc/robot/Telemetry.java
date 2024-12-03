package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.Odometery;
import frc.robot.Subsystems.Pigeon;

public class Telemetry {
    private static Field2d field;

    public static void init(){
        field = new Field2d();
        Vector2 position = Odometery.getPosition();
        field.setRobotPose(new Pose2d(new Translation2d(position.x, position.y), new Rotation2d(Pigeon.getRotation())));
        SmartDashboard.putData(field);
    }
    
    public static void update(){
        Vector2 position = Odometery.getPosition();
        field.setRobotPose(new Pose2d(new Translation2d(position.x, position.y), new Rotation2d(Pigeon.getRotation())));
        SmartDashboard.putData(field);
    }
}
