package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardDistance extends Command {

	private double distance;
	private DriveTrain dt;
	private double zeroLeft;
	private double zeroRight;

	public DriveForwardDistance(double distance) {
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		this.distance = distance;
		dt = (DriveTrain) Robot.getSubSystem(Subsystems.DRIVE_TRAIN);
		zeroLeft = dt.getLeftEncoder();
		zeroRight = dt.getRightEncoder();
	}

	@Override
	protected boolean isFinished() {
		return (dt.getLeftEncoder() + dt.getRightEncoder()) / 2 < distance;
	}

	@Override
	protected void end() {
		// TODO Find a way to report the error in the encoders
		double errorTheta = (dt.getRightEncoder() - dt.getLeftEncoder()) / dt.getWidth();
	}

}
