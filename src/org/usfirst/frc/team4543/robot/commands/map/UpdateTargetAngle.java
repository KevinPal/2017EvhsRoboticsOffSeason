package org.usfirst.frc.team4543.robot.commands.map;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.team4543.map.FieldMap;

import edu.wpi.first.wpilibj.command.Command;

public class UpdateTargetAngle extends Command {
	private double targetAngle;

	public UpdateTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}

	@Override
	protected void execute() {
		((FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP)).setTargetAngle(targetAngle);

	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
