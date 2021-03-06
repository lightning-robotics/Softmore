// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private static final TalonSRX frontRightMotor = new TalonSRX(Constants.DriveConstants.FRONT_RIGHT_MOTOR);
  private static final TalonSRX backRightMotor = new TalonSRX(Constants.DriveConstants.BACK_RIGHT_MOTOR);
  private static final TalonSRX frontLeftMotor = new TalonSRX(Constants.DriveConstants.FRONT_LEFT_MOTOR);
  private static final TalonSRX backLeftMotor = new TalonSRX(Constants.DriveConstants.BACK_LEFT_MOTOR);

  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    DriveTrain.frontLeftMotor.follow(DriveTrain.backLeftMotor);
    DriveTrain.backRightMotor.follow(DriveTrain.frontRightMotor);

    DriveTrain.backLeftMotor.setInverted(false);
    DriveTrain.backRightMotor.setInverted(true);
    DriveTrain.frontLeftMotor.setInverted(false);
    DriveTrain.frontRightMotor.setInverted(true);

    DriveTrain.frontLeftMotor.setNeutralMode(NeutralMode.Coast);
    DriveTrain.backLeftMotor.setNeutralMode(NeutralMode.Coast);
    DriveTrain.frontRightMotor.setNeutralMode(NeutralMode.Coast);
    DriveTrain.backRightMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setRightMotorSpeed(double speed) {
    backRightMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setLeftMotorSpeed(double speed) {
    frontLeftMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean getDeadzone(double speed) {
    return (Math.abs(speed) < Constants.DriveConstants.DEADZONE);
  }
}
