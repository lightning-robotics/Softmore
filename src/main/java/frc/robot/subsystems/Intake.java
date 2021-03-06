// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private static TalonSRX leftMotor = new TalonSRX(Constants.IntakeConstants.LEFT_INTAKE_MOTOR);
  private static TalonSRX rightMotor = new TalonSRX(Constants.IntakeConstants.RIGHT_INTAKE_MOTOR);
  private static TalonSRX centerMotor = new TalonSRX(Constants.IntakeConstants.CENTER_INTAKE_MOTOR);
  private static TalonSRX backMotor = new TalonSRX(Constants.IntakeConstants.BACK_INTAKE_MOTOR);


  /** Creates a new Intake. */
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void setSpeed(double speed) {
    leftMotor.set(ControlMode.PercentOutput, speed);
    rightMotor.set(ControlMode.PercentOutput, -speed);
    centerMotor.set(ControlMode.PercentOutput, -speed - 0.1);
    backMotor.set(ControlMode.PercentOutput, -speed);
  }
}
