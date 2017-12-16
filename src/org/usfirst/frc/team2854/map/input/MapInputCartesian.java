package org.usfirst.frc.team2854.map.input;

public interface MapInputCartesian extends MapInput {
	double deltaX = 0;
	double deltaY = 0;

	public double getDeltaX();

	public double getDeltaY();

	public double getDeltaRotation();

}