package org.usfirst.frc.team4543.robot.subsystems;

import org.usfirst.frc.team4543.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon leftT1, leftT2, rightT1, rightT2;
	private int leftT1Zero, leftT2Zero, rightT1Zero, rightT2Zero;
	// TODO put dimensions of the drivetrain in here
	private final double width;

	public void initDefaultCommand() {
	}

	public DriveTrain() {
		leftT1 = new CANTalon(RobotMap.leftTalonID1);
		leftT1.setInverted(true);

		leftT2 = new CANTalon(RobotMap.leftTalonID2);
		leftT2.setInverted(true);

		rightT1 = new CANTalon(RobotMap.rightTalonID1);
		rightT1.setInverted(false);

		rightT2 = new CANTalon(RobotMap.rightTalonID2);
		rightT2.setInverted(false);

		width = 100; // TODO put something here

	}

	public void drive(double left, double right) {
		leftT1.set(left);
		leftT2.set(left);
		rightT1.set(right);
		rightT2.set(right);
	}

	public void stop() {
		leftT1.set(0);
		leftT2.set(0);
		rightT1.set(0);
		rightT2.set(0);
	}

	public void zeroEncoders() {
		leftT1Zero = leftT1.getEncPosition();
		leftT2Zero = leftT2.getEncPosition();
		rightT1Zero = rightT1.getEncPosition();
		rightT2Zero = rightT2.getEncPosition();
	}

	public int getLeftEncoder() {
		return (int) Math.round(((leftT1.getEncPosition() - leftT1Zero) + (leftT2.getEncPosition() - leftT2Zero)) / 2);
	}

	public int getRightEncoder() {
		return (int) Math
				.round(((rightT1.getEncPosition() - rightT1Zero) + (rightT2.getEncPosition() - rightT2Zero)) / 2);
	}

	public double getWidth() {
		return width;
	}
}
