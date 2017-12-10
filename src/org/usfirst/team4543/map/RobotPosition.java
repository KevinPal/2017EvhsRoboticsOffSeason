package org.usfirst.team4543.map;

import java.awt.geom.Point2D;

public class RobotPosition extends Point2D {
	private double x;
	private double y;
	private double theta;

	RobotPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	RobotPosition() {
		x = 0;
		y = 0;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	public double getTheta() {
		return theta;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.x = y;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

}
