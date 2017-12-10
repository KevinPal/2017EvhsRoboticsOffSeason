package org.usfirst.frc.team4543.robot.commands.map;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;
import org.usfirst.team4543.map.FieldMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class UpdateRobotPositionEncoder extends Command {
	private double timeStamp;// TODO not sure if I'll need this again

	public UpdateRobotPositionEncoder() {
		timeStamp = Timer.getFPGATimestamp();
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
	}

	@Override
	protected void execute() {
		FieldMap fm = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		DriveTrain dt = (DriveTrain) Robot.getSubSystem(Subsystems.DOOR);
		double left = dt.getLeftEncoder();
		double right = dt.getRightEncoder();
		try {
			double r = Math.abs((dt.getWidth() / (right - left)) * (right + left) / 2);
			double theta = (right + left) / 2;
			fm.setRobotAngle(fm.getTheta() + (right + left) / (2 + r));
			fm.setRobotPosition(fm.getX() + (r + dt.getWidth()) / 2 * Math.cos(theta),
					fm.getY() + (r + dt.getWidth()) / 2 * Math.sin(theta));
		} catch (ArithmeticException e) {
			fm.setRobotAngle(fm.getTheta());
			double distance = (left + right) / 2;
			fm.setRobotPosition(fm.getX() + distance * Math.cos(fm.getTheta()),
					fm.getY() + distance * Math.sin(fm.getTheta()));
		}
		dt.zeroEncoders();
		timeStamp = Timer.getFPGATimestamp();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
