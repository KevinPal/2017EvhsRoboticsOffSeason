package org.usfirst.team4543.map;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class FieldLine extends Line2D {
	private FieldPoint p1, p2;

	private double getMax(double a, double b) {
		return (a > b) ? a : b;
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO I don't feel liek doing this rn, I dont think we'll need it
		return null;
	}

	@Override
	public Point2D getP1() {
		return p1;
	}

	@Override
	public Point2D getP2() {
		return p2;
	}

	@Override
	public double getX1() {
		return p1.getX();
	}

	@Override
	public double getX2() {
		return p2.getX();
	}

	@Override
	public double getY1() {
		return p1.getY();
	}

	@Override
	public double getY2() {
		return p2.getY();
	}

	@Override
	public void setLine(double x1, double x2, double y1, double y2) {
		p1 = new FieldPoint(x1, y1);
		p2 = new FieldPoint(x2, y2);
	}

}
