package org.usfirst.frc.team2854.map.pathGeneration;

public class CircularRepulsionVectorField extends EllipticalRepulsionVectorField {

	public CircularRepulsionVectorField(double xCoordinate, double yCoordinate, double constant) {
		super(xCoordinate, yCoordinate, constant, constant);
	}

}
