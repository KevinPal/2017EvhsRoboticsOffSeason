package org.usfirst.frc.team2854.map.pathGeneration;

import java.util.function.Function;

import org.usfirst.frc.team2854.map.elements.Field;
import org.usfirst.frc.team2854.map.elements.FieldMap;
import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.math.Vector;

public class BoundaryRepulsionVectorField implements Function<RobotPosition, Vector> {
	private Field fm;
	private double constant;
	private double degree;

	public BoundaryRepulsionVectorField(Field fm, double constant) {
		this.fm = fm;
		this.constant = constant;
		degree = 2;
	}

	public BoundaryRepulsionVectorField(Field fm, double constant, double degree) {
		this.fm = fm;
		this.constant = constant;
		this.degree = degree;
	}

	private double getMagnitude(double r) {
		return Math.pow(r, -degree) * constant;
	}

	private Vector horizontalVector(RobotPosition rp) {
		return (new Vector(1, 0)).multiply(getMagnitude(rp.getX()))
				.add(new Vector(-1, 0).multiply(getMagnitude(fm.getWidth() - rp.getX())));
	}

	private Vector verticalVector(RobotPosition rp) {
		return (new Vector(0, 1)).multiply(getMagnitude(rp.getY()))
				.add(new Vector(0, -1).multiply(getMagnitude(fm.getHeight() - rp.getY())));
	}

	@Override
	public Vector apply(RobotPosition rp) {
		return verticalVector(rp).add(horizontalVector(rp));
	}

}
