package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SensorBoard;
import org.usfirst.frc.team2854.robot.SubsystemNames;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command {

	DriveTrain drive;
	private double targetAngle, startingAngle, currentAngle, angleDiff, minPower, maxPower;
	private SensorBoard sensors;

	public TurnToAngle(double targetAngle, double minPower, double maxPower) {
		requires(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
		this.targetAngle = targetAngle;
		this.minPower = minPower;
		this.maxPower = maxPower;
	}

	protected void initialize() {

		drive = (DriveTrain) Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN);
		sensors = Robot.getSensors();
		startingAngle = sensors.getNavX().getAngle();
	}

	protected void execute() {
		currentAngle = sensors.getNavX().getAngle();
		angleDiff = targetAngle - currentAngle;

		SmartDashboard.putNumber("TargetAngle", targetAngle);
		SmartDashboard.putNumber("CurrentAngle", currentAngle);
		SmartDashboard.putNumber("StartingAngle", startingAngle);
		SmartDashboard.putNumber("AngleDiff", angleDiff);
		double b = (maxPower - minPower) / 180 / 180;
		drive.drive(-((angleDiff > 0 ? 1 : -1) * (Math.pow(angleDiff, 2) * b + minPower)),
				(angleDiff > 0 ? 1 : -1) * (Math.pow(angleDiff, 2) * b + minPower));
		// TODO I guess traditionally, we'd want to use an exponential function, but I
		// didn't feel like working with odd functions, Maybe e^(x^2) would work better
		// idk
	}

	protected boolean isFinished() {
		return Math.abs(angleDiff) % 360 < .5f;
	}

	protected void end() {
		System.out.println("Ending");
	}

	protected void interrupted() {
	}
}
