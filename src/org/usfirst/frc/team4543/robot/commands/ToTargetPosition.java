package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.team4543.map.FieldMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ToTargetPosition extends Command {

	private double distanceThreshold;
	private FieldMap fm;

	public ToTargetPosition(double distanceThreshold) {
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		fm = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		this.distanceThreshold = distanceThreshold;
	}

	@Override
	protected void execute() {
		double distance = Math
				.sqrt(Math.pow(fm.getTargetX() - fm.getX(), 2) + Math.pow(fm.getTargetX() - fm.getY(), 2));
		double angle = Math.atan((fm.getY() - fm.getTargetY()) / (fm.getX() - fm.getTargetX()));
		Scheduler.getInstance().add(new DriveAngleDistance(angle, distance));
	}

	@Override
	protected boolean isFinished() {
		return ((Math.pow(fm.getTargetX() - fm.getX(), 2)
				+ Math.pow(fm.getTargetX() - fm.getY(), 2)) < distanceThreshold * distanceThreshold);
	}

	@Override
	protected void end() {
		Scheduler.getInstance().add(new Stop());
	}

}
