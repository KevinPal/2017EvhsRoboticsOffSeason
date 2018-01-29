package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.JoystickDrive;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private TalonSRX leftT1, leftT2, rightT1, rightT2;
	private double zeroLeft, zeroRight, width;
	private boolean side = false;

	private DoubleSolenoid shifter;

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
	}

	public DriveTrain() {
		leftT1 = new TalonSRX(RobotMap.leftTalonID1);
		leftT1.setInverted(side);

		leftT2 = new TalonSRX(RobotMap.leftTalonID2);
		leftT2.setInverted(side);

		rightT1 = new TalonSRX(RobotMap.rightTalonID1);
		rightT1.setInverted(!side);

		rightT2 = new TalonSRX(RobotMap.rightTalonID2);
		rightT2.setInverted(!side);

		shifter = new DoubleSolenoid(RobotMap.shifterUp, RobotMap.shifterDown);

		zeroRight = 0;
		zeroLeft = 0;
		width = 10;

	}

	public void drive(double left, double right) {
		leftT1.set(ControlMode.PercentOutput, left);
		leftT2.set(ControlMode.PercentOutput, left);
		rightT1.set(ControlMode.PercentOutput, right);
		rightT2.set(ControlMode.PercentOutput, right);
	}

	public void drive(ControlMode cm, double left, double right) {
		leftT1.set(cm, left);
		leftT2.set(cm, left);
		rightT2.set(cm, right);
		rightT1.set(cm, right);
	}

	public void toggleShift() {
		if (shifter.get() == Value.kForward) {
			shifter.set(Value.kReverse);
		} else if (shifter.get() == Value.kReverse) {
			shifter.set(Value.kForward);
		} else {
			System.out.println("Weird state " + shifter.get().toString() + " defualting to forward");
			shifter.set(Value.kForward);
		}
	}

	public void shiftUp() {
		System.out.println("Shifting up");
		shifter.set(Value.kForward);
	}

	public void shiftDown() {
		System.out.println("shifting down");
		shifter.set(Value.kReverse);
	}

	public Value getState() {
		return shifter.get();
	}

	public void stop() {
		leftT1.set(ControlMode.PercentOutput, 0.0);
		leftT2.set(ControlMode.PercentOutput, 0.0);
		rightT1.set(ControlMode.PercentOutput, 0.0);
		rightT2.set(ControlMode.PercentOutput, 0.0);
	}

	public double getLeftEncoder() {
		return leftT2.getSelectedSensorPosition(0) - zeroLeft;
	}

	public double getRightEncoder() {
		return rightT2.getSelectedSensorPosition(0) - zeroRight;
	}

	public void zeroEncoders() {
		zeroLeft = getLeftEncoder();
		zeroRight = getRightEncoder();

	}

	public double getWidth() {
		return width;
	}
}
