package org.usfirst.frc.team4543.map;

public class FieldCircle extends FieldEllipse {

	public FieldCircle(double radius) {
		super(new Vector(radius, 0), new Vector(0, radius));
	}

}
