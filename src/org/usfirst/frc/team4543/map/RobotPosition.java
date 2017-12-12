package org.usfirst.frc.team4543.map;

import java.awt.geom.Point2D;

public class RobotPosition extends Vector {
	private double theta;

	RobotPosition(double x, double y) {
		super(x, y);
	}

	RobotPosition() {
		super(0, 0);
	}

	public double getTheta() {
		return theta;

	}

	public void setLocation(double x, double y) {
		super.x = x;
		super.x = y;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	public void rotate(double theta) {
		this.theta += theta;
	}
}
