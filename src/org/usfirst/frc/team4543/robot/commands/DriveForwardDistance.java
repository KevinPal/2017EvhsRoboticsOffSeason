package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardDistance extends Command {

	public DriveForwardDistance() {
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
