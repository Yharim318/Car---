package frc.robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class TeleopCar extends InstantCommand {
  Car car;
  DoubleSupplier x;
  DoubleSupplier y;
  BooleanSupplier brake;
  double wantedL;
  double wantedR;
  public TeleopCar(Car car_, DoubleSupplier x_, DoubleSupplier y_, BooleanSupplier brake_) {
    addRequirements(car_);
    car = car_;

    x = x_;
    y = y_;
    brake = brake_;
  }

  @Override
  public void execute() {
    double xVal = x.getAsDouble();
    double yVal = y.getAsDouble();
    Boolean brakeVal = brake.getAsBoolean();

    wantedL = Car.maxSpeed * (yVal - 0.5 * xVal);
    wantedR = Car.maxSpeed * (yVal + 0.5 * xVal);
    if(brakeVal)
      car.setNeutralValue(NeutralModeValue.Brake);
    else
      car.setNeutralValue(NeutralModeValue.Coast);

    car.addLeftVoltage(wantedL);
    car.addRightVoltage(wantedR);
  }
}
