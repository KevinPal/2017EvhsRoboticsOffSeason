package com.team2854.builtincommands;

import edu.wpi.first.wpilibj.command.Command;

public abstract class DriveCommand extends Command{

	protected double distance;
	
	public DriveCommand(double distance) {
		this.distance = distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
