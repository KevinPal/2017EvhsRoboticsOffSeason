package org.usfirst.frc.team4543.map;

public class FieldPolygon implements FieldShape {
	public FieldPolygon(double[] x, double[] y, int points) {
		super();
	}

	@Override
	public boolean isWithinBounds(RobotPosition rp) {
		// TODO It doesn't seem that the geom package has a Polygon class
		return true;
	}

}
