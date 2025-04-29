// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Relay.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Car extends SubsystemBase {
  /** Creates a new Car. */
  TalonFX left1;
  TalonFX left2;
  TalonFX right1;
  TalonFX right2;
  TalonFXConfiguration configL = new TalonFXConfiguration();
  TalonFXConfiguration configR = new TalonFXConfiguration();
  AnalogInput pressure = new AnalogInput(0);
  Relay output = new Relay(0);
  double speed = 0;
  double wantedSpeedLeft = 0;
  double wantedSpeedRight = 0;
  public static double maxSpeed = 12;
  public Car() {
    configL.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    configL.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    configR.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    configR.MotorOutput.NeutralMode = NeutralModeValue.Coast;

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
    left1.setVoltage(speed);
    left2.setVoltage(speed);
    right1.setVoltage(speed);
    right2.setVoltage(speed);
  }
  public void setLeftVoltage(double v){
    wantedSpeedLeft = v;
  }
  public void setRightVoltage(double v){
    wantedSpeedRight = v;
  }
  public void addLeftVoltage(double v){
    wantedSpeedLeft += v * Robot.kDefaultPeriod;
  }
  public void addRightVoltage(double v ){
    wantedSpeedRight += v * Robot.kDefaultPeriod;
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
  public double sensorToPsi(double value){
    return 250 * (value/5) - 25; // Change based on sensor 
  }
  void toggleOutput(){
    if (output.get() == Value.kOn){
      output.set(Value.kOff);
    }
    else{
      output.set(Value.kOn);
    }
  }
  void toggleOutput(Value value){
    output.set(value);
  }
}
