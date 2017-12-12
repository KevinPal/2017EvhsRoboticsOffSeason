package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.map.FieldMap;
import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class ToTargetAngle extends Command {
	private FieldMap fm;
	private DriveTrain dt;
	private double targetThreshold;

	public ToTargetAngle() {
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
		fm = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		dt = (DriveTrain) Robot.getSubSystem(Subsystems.DRIVE_TRAIN);
		targetThreshold = 0.1;
	}

	public ToTargetAngle(double targetThreshold) {
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
		fm = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		dt = (DriveTrain) Robot.getSubSystem(Subsystems.DRIVE_TRAIN);
		this.targetThreshold = targetThreshold;
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return (fm.getTargetPosition().getTheta() - fm.getRobotPosition().getTheta()) < targetThreshold;
	}

}
