package org.usfirst.frc.team4543.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAngleDistance extends CommandGroup {
	public DriveAngleDistance(double distance, double angle) {
		addSequential(new TurnToAngle(angle));
		addSequential(new DriveForwardDistance(distance, 0.1));
	}
}
