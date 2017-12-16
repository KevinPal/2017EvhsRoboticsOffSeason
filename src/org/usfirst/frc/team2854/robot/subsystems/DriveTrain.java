package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.commands.JoystickDrive;
import org.usfirst.frc.team4543.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon leftT1, leftT2, rightT1, rightT2;
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


	}

	public void drive(double left, double right) {
		leftT1.set(left);
		leftT2.set(left);
		rightT1.set(right);
		rightT2.set(right);
	}
	
	public void shiftUp() {
		shifter.set(Value.kForward);
	}
	
	public void shiftDown() {
		System.out.println("shifting down");
		shifter.set(Value.kReverse);
	}

	public void stop() {
		leftT1.set(0);
		leftT2.set(0);
		rightT1.set(0);
		rightT2.set(0);
	}

	public CANTalon getLeftT1() {
		return leftT1;
	}

	public CANTalon getRightT1() {
		return rightT1;
	}

}
