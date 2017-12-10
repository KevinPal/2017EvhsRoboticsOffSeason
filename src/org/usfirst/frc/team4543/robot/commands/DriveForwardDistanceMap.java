package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardDistanceMap extends Command {
	public DriveForwardDistanceMap(double distanceOut, double power) {
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
