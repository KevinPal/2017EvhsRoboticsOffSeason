package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class UpdateRobotPosition extends Command {

	public UpdateRobotPosition() {
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
