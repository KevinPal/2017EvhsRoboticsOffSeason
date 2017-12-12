package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.map.FieldMap;
import org.usfirst.frc.team4543.map.RobotPosition;
import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveForwardDistanceMap extends CommandGroup {
	public DriveForwardDistanceMap(double distanceOut, double power) {
		FieldMap fm = (FieldMap) Robot.getSubSystem(Subsystems.FIELD_MAP);
		RobotPosition rp = fm.getRobotPosition();
		fm.setTargetPosition(rp.getX() + distanceOut * Math.cos(rp.getTheta()),
				rp.getY() + distanceOut * Math.cos(rp.getTheta()), false);
		addSequential(new ToTargetPosition(power));
	}

}
