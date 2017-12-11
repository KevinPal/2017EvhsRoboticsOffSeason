package org.usfirst.frc.team4543.map;

import java.awt.geom.Point2D;

public class FieldPoint extends Point2D {
	private double x, y;

	public FieldPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
