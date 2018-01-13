package org.usfirst.frc.team2854.map.math;

public class UnitVector extends Vector {
	public UnitVector(double x, double y) {
		double magnitude = Math.sqrt(x * x + y * y);
		x /= magnitude;
		y /= magnitude;
	}

}
