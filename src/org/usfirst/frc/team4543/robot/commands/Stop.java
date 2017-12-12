package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Stop extends Command {

	public Stop() {
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		((DriveTrain) Robot.getSubSystem(Subsystems.DRIVE_TRAIN)).stop();;
	}

}
