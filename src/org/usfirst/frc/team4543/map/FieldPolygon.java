package org.usfirst.frc.team4543.map;

import java.awt.Color;

import org.opencv.core.Mat;

public class FieldPolygon implements FieldShape {
	public FieldPolygon(double[] x, double[] y, int points) {
		super();
	}

	@Override
	public boolean isWithinBounds(RobotPosition rp) {
		// TODO It doesn't seem that the geom package has a Polygon class
		return true;
	}

	@Override
	public void draw(Mat m, Vector translation, Color c) {
		// TODO Auto-generated method stub
		
	}

}
