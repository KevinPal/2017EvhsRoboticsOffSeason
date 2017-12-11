package org.usfirst.team4543.map;

import java.awt.Polygon;
import java.util.ArrayList;

public class FieldPolygon extends Polygon implements FieldShape {
	public FieldPolygon(double[] x, double[] y, int points) {

	}

	@Override
	public boolean isWithinBounds(RobotPosition rp) {
		return contains(rp.getX(), rp.getY());
	}

}
