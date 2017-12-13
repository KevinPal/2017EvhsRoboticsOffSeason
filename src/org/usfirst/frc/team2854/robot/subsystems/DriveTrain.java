package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.JoystickDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon leftT1, leftT2, rightT1, rightT2;

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());
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

}
