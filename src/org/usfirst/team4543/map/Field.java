package org.usfirst.team4543.map;

import java.awt.geom.Dimension2D;

class Field extends Dimension2D {
	private double width;
	private double height;

	Field(double width, double height) {
		this.width = width;
		this.height = height;
	}

	Field() {
		width = 10;
		height = 10;
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

}
