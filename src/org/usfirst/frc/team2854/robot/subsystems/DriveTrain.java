package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.JoystickDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon leftT1, leftT2, rightT1, rightT2;
	private double zeroLeft, zeroRight, width;
	private boolean side = false;

	private DoubleSolenoid shifter;

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
	}

	public DriveTrain() {
		leftT1 = new CANTalon(RobotMap.leftTalonID1);
		leftT1.setInverted(side);

		leftT2 = new CANTalon(RobotMap.leftTalonID2);
		leftT2.setInverted(side);

		rightT1 = new CANTalon(RobotMap.rightTalonID1);
		rightT1.setInverted(!side);

		rightT2 = new CANTalon(RobotMap.rightTalonID2);
		rightT2.setInverted(!side);

		shifter = new DoubleSolenoid(RobotMap.shifterUp, RobotMap.shifterDown);

		zeroRight = 0;
		zeroLeft = 0;
		width = 10;

	}

	public void drive(double left, double right) {
		leftT1.set(left);

		leftT2.set(left);
		rightT1.set(right);
		rightT2.set(right);
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
		leftT1.set(0);
		leftT2.set(0);
		rightT1.set(0);
		rightT2.set(0);
	}

	public double getLeftEncoder() {
		return leftT1.getEncPosition() - zeroLeft;
	}

	public double getRightEncoder() {
		return rightT1.getEncPosition() - zeroRight;
	}

	public void zeroEncoders() {
		zeroLeft = getLeftEncoder();
		zeroRight = getRightEncoder();

	}

	public double getWidth() {
		return width;
	}
}
