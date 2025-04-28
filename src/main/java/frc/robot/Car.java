// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Car extends SubsystemBase {
  /** Creates a new Car. */
  TalonFX left1;
  TalonFX left2;
  TalonFX right1;
  TalonFX right2;
  TalonFXConfiguration configL = new TalonFXConfiguration();
  TalonFXConfiguration configR = new TalonFXConfiguration();
  VelocityVoltage PID = new VelocityVoltage(0);
  double wantedSpeedLeft = 0;
  double wantedSpeedRight = 0;
  public static double maxSpeed = 200;
  public Car() {
    configL.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    configL.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    configL.Slot0.kP = 1;
    configL.Slot0.kI = 0;
    configL.Slot0.kD = 0;

    configR.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    configR.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    configR.Slot0.kP = 1;
    configR.Slot0.kI = 0;
    configR.Slot0.kD = 0;

    left1 = new TalonFX(0);
    left2 = new TalonFX(0);
    right1 = new TalonFX(0);
    right2 = new TalonFX(0);

    left1.getConfigurator().apply(configL);
    left2.getConfigurator().apply(configL);
    right1.getConfigurator().apply(configR);
    right2.getConfigurator().apply(configR);
  }

  @Override
  public void periodic() {
    left1.setControl(PID.withVelocity(wantedSpeedLeft));
    left2.setControl(PID.withVelocity(wantedSpeedLeft));
    right1.setControl(PID.withVelocity(wantedSpeedRight));
    right2.setControl(PID.withVelocity(wantedSpeedRight));
  }
  public void setLeftVelocity(double v){
    wantedSpeedLeft = v;
  }
  public void setRightVelocity(double v){
    wantedSpeedRight = v;
  }
  public double getLeftVelocity(){
    return left1.getVelocity().getValueAsDouble();
  }
  public double getLRightVelocity(){
    return right1.getVelocity().getValueAsDouble();
  }
  public void setNeutralValue(NeutralModeValue value){
    left1.setNeutralMode(value);
    left2.setNeutralMode(value);
    right1.setNeutralMode(value);
    right2.setNeutralMode(value);
  }
}
