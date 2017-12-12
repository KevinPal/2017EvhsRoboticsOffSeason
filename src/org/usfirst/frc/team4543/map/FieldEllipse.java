package org.usfirst.frc.team4543.map;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class FieldEllipse extends Ellipse2D implements FieldShape {// TODO Do this later,
	private Vector major, minor;
	private double x0, y0;

	public FieldEllipse(Vector major, Vector minor, double x0, double y0) {
		if (Vector.dot(major, minor) == 0) {
			this.major = major;
			this.minor = minor;
			this.x0 = x0;
			this.y0 = y0;
		} else {
			System.out.println("no");
		}
	}

	public FieldEllipse(double major, double minor, double theta) {
		// TODO make this later
	}

	@Override
	public boolean isWithinBounds(RobotPosition rp) {
		Vector u = new Vector(rp.getX() - x0, rp.getY() - y0);
		double quantity = Math.pow(Vector.dot(u, major) / Math.pow(major.getMagnitude(), 2), 2)
				+ Math.pow(Vector.dot(u, minor) / Math.pow(minor.getMagnitude(), 2), 2);
		return (quantity <= 1);
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFrame(double arg0, double arg2, double arg4, double arg6) {
		// TODO Auto-generated method stub

	}

}
