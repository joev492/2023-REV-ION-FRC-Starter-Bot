// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutonomousDrive extends CommandBase {
  private final Timer m_timer = new Timer();
  private final DrivetrainSubsystem m_drivetrain;
  /** Creates a new AutonomousDrive. */
  public AutonomousDrive(DrivetrainSubsystem driveSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = driveSubsystem;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.restart();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_timer.get() < 3)
    m_drivetrain.driveArcade(55, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
