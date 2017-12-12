package org.usfirst.frc.team4543.map;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class Field extends Rectangle2D implements FieldShape {
	private double width;
	private double height;
	private ArrayList<FieldShape> innerFieldPieces;

	Field(double width, double height) {
		this.width = width;
		this.height = height;
		innerFieldPieces = new ArrayList<FieldShape>();
	}

	Field() {
		width = 10;
		height = 10;
		innerFieldPieces = new ArrayList<FieldShape>();
	}

	private boolean isOutOfInnerPieces(RobotPosition rp) {
		for (FieldShape fs : innerFieldPieces) {
			if (fs.isWithinBounds(rp)) {
				return false;
			}
		}
		return true;
	}

	public boolean isWithinBounds(RobotPosition rp) {
		double x = rp.getX();
		double y = rp.getY();
		return isOutOfInnerPieces(rp) && contains(rp.getX(), rp.getY());
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public void addFieldShape(FieldShape fs) {
		innerFieldPieces.add(fs);
	}

	@Override
	public Rectangle2D createIntersection(Rectangle2D arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int outcode(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRect(double arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
