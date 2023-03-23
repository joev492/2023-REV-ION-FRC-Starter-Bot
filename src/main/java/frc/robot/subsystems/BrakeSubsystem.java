// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BrakeSubsystem extends SubsystemBase {
  private final DoubleSolenoid leftBrakes;
  private final DoubleSolenoid rightBrakes;

  /** Creates a new BrakeSubsystem. */
  public BrakeSubsystem() {
    leftBrakes = new DoubleSolenoid(2, PneumaticsModuleType.REVPH, 
    Constants.Brake.kDoubleSolenoid1Extend, Constants.Brake.kDoubleSolenoid1Retract);   
  
    rightBrakes = new DoubleSolenoid(2, PneumaticsModuleType.REVPH, 
    Constants.Brake.kDoubleSolenoid2Extend, Constants.Brake.kDoubleSolenoid2Retract);
  }

  public void brakingOn() {
    leftBrakes.set(Value.kForward);
    rightBrakes.set(Value.kForward);
  }

  public void brakingOff() {
    leftBrakes.set(Value.kReverse);
    rightBrakes.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
