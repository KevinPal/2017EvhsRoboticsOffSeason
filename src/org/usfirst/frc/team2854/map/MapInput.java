package org.usfirst.frc.team2854.map;

public interface MapInput {

	public double getDeltaForward();
	
	public double getError();
	
	public double getRotation();
	
	public void update();
	
}
