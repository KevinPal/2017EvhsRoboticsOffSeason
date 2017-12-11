package org.usfirst.frc.team4543.map;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class FieldEllipse extends Ellipse2D implements FieldShape {// TODO Do this later,
	public FieldEllipse(Vector major, Vector minor) {

	}

	@Override
	public boolean isWithinBounds(RobotPosition rp) {
		return contains(rp.getX(), rp.getY());
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
