package org.usfirst.frc.team4543.robot.commands.map;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;
import org.usfirst.team4543.map.FieldMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class UpdateRobotAngleAndRobotPositionEncoder extends Command {
	private double timeStamp;// TODO not sure if I'll need this again
	private FieldMap fm;
	private DriveTrain dt;

	public UpdateRobotAngleAndRobotPositionEncoder() {
		timeStamp = Timer.getFPGATimestamp();
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
		fm = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		dt = (DriveTrain) Robot.getSubSystem(Subsystems.DOOR);
	}

	@Override
	protected void execute() {
		double left = dt.getLeftEncoder();
		double right = dt.getRightEncoder();
		try {
			double r = Math.abs((dt.getWidth() / (right - left)) * (right + left) / 2);
			double theta = (right - left) / 2;
			fm.setRobotAngle(fm.getRobotPosition().getTheta() + (right + left) / (2 + r));
			fm.setRobotPosition(fm.getRobotPosition().getX() - (r * (1 - Math.cos(theta)) + dt.getWidth() / 2),
					fm.getRobotPosition().getY() + (r * Math.sin(theta)));
		} catch (ArithmeticException e) {
			fm.setRobotAngle(fm.getRobotPosition().getTheta());
			double distance = (left + right) / 2;
			fm.setRobotPosition(fm.getRobotPosition().getX() + distance * Math.cos(fm.getRobotPosition().getTheta()),
					fm.getRobotPosition().getY() + distance * Math.sin(fm.getRobotPosition().getTheta()));
		}
		dt.zeroEncoders();
		timeStamp = Timer.getFPGATimestamp();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
