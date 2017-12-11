package org.usfirst.team4543.map;

import java.awt.geom.Dimension2D;
import java.util.ArrayList;

class Field extends Dimension2D implements FieldShape {
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
		return isOutOfInnerPieces(rp) && !(x < 0 || y < 0 || x > getWidth() || y > getHeight());
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public void addFieldShape(FieldShape fs) {
		innerFieldPieces.add(fs);
	}
}
