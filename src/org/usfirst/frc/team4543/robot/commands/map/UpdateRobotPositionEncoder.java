package org.usfirst.frc.team4543.robot.commands.map;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;
import org.usfirst.team4543.map.FieldMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class UpdateRobotPositionEncoder extends Command {
	private double timeStamp;

	public UpdateRobotPositionEncoder() {
		timeStamp = Timer.getFPGATimestamp();
		requires(Robot.getSubSystem(Subsystems.FIELD_MAP));
		requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
	}

	@Override
	protected void execute() {
		FieldMap fm = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		DriveTrain dt = (DriveTrain) Robot.getSubSystem(Subsystems.DOOR);

		fm.setRobotPosition(fm.getX() + dt.getLeftEncoder() * (Timer.getFPGATimestamp() - timeStamp),
				fm.getY() + dt.getRightEncoder() * (Timer.getFPGATimestamp() - timeStamp));
		//TODO This is an approximation, make maths that assume no slipping
		dt.zeroEncoders();
		timeStamp = Timer.getFPGATimestamp();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
