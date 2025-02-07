// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Commands.AutonomousDrive;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.BrakeSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final GripperSubsystem m_gripper = new GripperSubsystem();
  private final ArmSubsystem m_arm = new ArmSubsystem();
  private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
  private final BrakeSubsystem m_brakes = new BrakeSubsystem();

  private final AutonomousDrive auto_drive = new AutonomousDrive(m_drivetrain);

  private Joystick m_driveController = new Joystick(Constants.OIConstants.kDriverController); 
  private XboxController m_assistantController = new XboxController(Constants.OIConstants.kAssistantController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings(); 
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //set up the drivetrain command that runs all the time
    m_drivetrain.setDefaultCommand(new RunCommand(
      () -> 
        m_drivetrain.driveArcade(
          MathUtil.applyDeadband(- m_driveController.getY(), Constants.OIConstants.kDriveDeadband),
          MathUtil.applyDeadband(- m_driveController.getX() * Constants.Drivetrain.kTurningScale, Constants.OIConstants.kDriveDeadband))
      , m_drivetrain)
    );

    //set up gripper open/close
    new JoystickButton(m_assistantController, XboxController.Button.kRightBumper.value)
      .onTrue(new InstantCommand(() -> m_gripper.openGripper()))
      .onFalse(new InstantCommand(() -> m_gripper.closeGripper()));

  /*
    //set up alternate gripper open/close UNSAFE
    new JoystickButton(m_driveController, 10)
      .onTrue(new InstantCommand(() -> m_gripper.unsafeOpenGripper()));
    new JoystickButton(m_driveController, 11)
      .onTrue(new InstantCommand(() -> m_gripper.unsafeCloseGripper()));
  */

    //set up arm preset positions
    new JoystickButton(m_assistantController, XboxController.Button.kA.value)
      .onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kHomePosition, m_gripper)));
    new JoystickButton(m_assistantController, XboxController.Button.kX.value)
      .onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kScoringPosition, m_gripper)));
    new JoystickButton(m_assistantController, XboxController.Button.kY.value)
      .onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kIntakePosition, m_gripper)));
    new JoystickButton(m_assistantController, XboxController.Button.kB.value)
      .onTrue(new InstantCommand(() -> m_arm.setTargetPosition(Constants.Arm.kFeederPosition, m_gripper)));

    //set up arm manual and auto functions
    m_arm.setDefaultCommand(new RunCommand(
      () ->
        m_arm.runAutomatic()
      , m_arm)
    );
    new Trigger(() -> 
      Math.abs(m_assistantController.getRightTriggerAxis() - m_assistantController.getLeftTriggerAxis()) > Constants.OIConstants.kArmManualDeadband
      ).whileTrue(new RunCommand(
        () ->
          m_arm.runManual((- m_assistantController.getRightTriggerAxis() + m_assistantController.getLeftTriggerAxis()) * Constants.OIConstants.kArmManualScale)
        , m_arm)); //was m_assistant.grta() - m_assistant.glta()

    //set up braking commands
    //TODO: Determine Joystick Buttons for Braking, Add to Constants.java
    new JoystickButton(m_driveController, 1)
      .onTrue(new InstantCommand(() -> m_brakes.brakingOn()));
    new JoystickButton(m_driveController, 2)
      .onTrue(new InstantCommand(() -> m_brakes.brakingOff()));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {   
    return auto_drive;
  }
}
