package org.usfirst.frc.team2854.map.pathGeneration;

import java.util.function.Function;

import org.usfirst.frc.team2854.map.elements.Field;
import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.math.Vector;

public class BoundaryRepulsionVectorField implements Function<RobotPosition, Vector> {
	private Field f;
	private double constant;
	private double degree;

	public BoundaryRepulsionVectorField(Field f, double constant) {
		this.f = f;
		this.constant = constant;
		degree = 2;
	}

	public BoundaryRepulsionVectorField(Field f, double constant, double degree) {
		this.f = f;
		this.constant = constant;
		this.degree = degree;
	}

	private double getMagnitude(double r) {
		return Math.pow(r, -degree) * constant;
	}

	public Vector horizontalVector(RobotPosition rp) {
		return (new Vector(1, 0)).multiply(getMagnitude(rp.getX()))
				.add(new Vector(-1, 0).multiply(getMagnitude(f.getWidth() - rp.getX())));
	}

	private Vector verticalVector(RobotPosition rp) {
		return (new Vector(0, 1)).multiply(getMagnitude(rp.getY()))
				.add(new Vector(0, -1).multiply(getMagnitude(f.getHeight() - rp.getY())));
	}

	@Override
	public Vector apply(RobotPosition rp) {
		return verticalVector(rp).add(horizontalVector(rp));
	}

}
