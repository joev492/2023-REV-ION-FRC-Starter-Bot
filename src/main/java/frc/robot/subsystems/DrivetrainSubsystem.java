// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

import edu.wpi.first.util.sendable.SendableBuilder;

public class DrivetrainSubsystem extends SubsystemBase {
  private Spark m_frontLeftMotor;
  private Spark m_frontRightMotor;
  private Spark m_rearLeftMotor;
  private Spark m_rearRightMotor;

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    m_frontLeftMotor  = new Spark(Constants.Drivetrain.kFrontLeftPWMId);
    m_frontLeftMotor.setInverted(Constants.Drivetrain.kFrontLeftInverted);

    m_frontRightMotor = new Spark(Constants.Drivetrain.kFrontRightPWMId);
    m_frontRightMotor.setInverted(Constants.Drivetrain.kFrontRightInverted);

    m_rearLeftMotor = new Spark(Constants.Drivetrain.kRearLeftPWMId);
    m_rearLeftMotor.setInverted(Constants.Drivetrain.kRearLeftInverted);

    m_rearRightMotor = new Spark(Constants.Drivetrain.kRearRightPWMId);
    m_rearRightMotor.setInverted(Constants.Drivetrain.kRearRightInverted);
  }

  public void driveArcade(double _straight, double _turn) {
    double left  = MathUtil.clamp(_straight + _turn, -1.0, 1.0);
    double right = MathUtil.clamp(_straight - _turn, -1.0, 1.0);

    m_frontLeftMotor.set(left);
    m_frontRightMotor.set(right);
    m_rearLeftMotor.set(left);
    m_rearRightMotor.set(right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    //builder.addDoubleProperty("Setpoint", () -> m_setpoint, (val) -> m_setpoint = val);
    //builder.addBooleanProperty("At Setpoint", () -> atSetpoint(), null);
    //addChild("Controller", m_controller);
  }
}
