package org.usfirst.frc.team2854.map.input;

public interface MapInput {

	public double getDeltaForward();

	public double getPosError();

	public double getRotation();

	public double getDeltaX();

	public double getDeltaY();

	public double getDeltaRotation();

	public void update();

}
