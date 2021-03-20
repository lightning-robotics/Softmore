// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // private final Challenge1 m_autoCommand = new Challenge1("Blue_path");

  public static final DriveTrain driveTrain = new DriveTrain();
  public static final Intake intake = new Intake();
  public static final Limelight limelight = new Limelight();

  public static final Command tankDrive = new TankDrive();
  public static final IntakeIn intakeIn = new IntakeIn();

  public static XboxController driver = new XboxController(Constants.PortConstants.ROBOT_DRIVE_CONTROLLER);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // Trajectory trajectory = new Trajectory();
    // Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(Constants.WeaverConstants.FAKE_PATH);
    // try {
    //   trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    // } catch (IOException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    // System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    // System.out.println(trajectory);
    // if (limelight.isVisible()) {
    //   // get the red path
    //   Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(Constants.WeaverConstants.SEARCH_ARED_JSON);
    //   try {
    //     trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    //   } catch (IOException e) {
    //     // TODO Auto-generated catch block
    //     e.printStackTrace();
    //   }
    // } else {
    //     // get the blue path
    //     Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(Constants.WeaverConstants.SEARCH_ABLUE_JSON);
    //     try {
    //       trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    //     } catch (IOException e) {
    //       // TODO Auto-generated catch block
    //       e.printStackTrace();
    //     }
    // }
    
    TrajectoryConfig config = new TrajectoryConfig(
      Units.feetToMeters(Constants.WeaverConstants.maxVelocity), 
      Units.feetToMeters(Constants.WeaverConstants.maxAcceleration)
    );
    config.setKinematics(driveTrain.getKinematics());

    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
      new Pose2d(), 
      List.of(
        new Translation2d(1, 1),
        new Translation2d(2, -1)
      ), 
      new Pose2d(new Translation2d(3, 0), new Rotation2d()), 
      config
    );

    RamseteCommand command = new RamseteCommand(
      trajectory,
      driveTrain::getPose,
      new RamseteController(2.0, 0.7),
      driveTrain.getFeedforward(),
      driveTrain.getKinematics(),
      driveTrain::getSpeeds,
      driveTrain.getLeftPIDController(),
      driveTrain.getRightPIDController(),
      driveTrain::setOutput,
      driveTrain
    );
    System.out.println(command);
    return command;
  }

  public static double getControllerAxis(XboxController controller, int axis) {
    return controller.getRawAxis(axis);
  }
}
