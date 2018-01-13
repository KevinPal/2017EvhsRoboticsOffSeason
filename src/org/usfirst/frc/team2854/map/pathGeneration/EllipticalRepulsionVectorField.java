package org.usfirst.frc.team2854.map.pathGeneration;

import java.util.function.Function;

import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.math.UnitVector;
import org.usfirst.frc.team2854.map.math.Vector;

public class EllipticalRepulsionVectorField implements Function<RobotPosition, Vector> {
	private double xConstant, yConstant;
	protected double xCoordinate, yCoordinate;
	private double degree;

	public EllipticalRepulsionVectorField(double xCoordinate, double yCoordinate, double xConstant, double degree,
			double yConstant) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.xConstant = xConstant;
		this.yConstant = xConstant;
		this.degree = degree;
	}

	public EllipticalRepulsionVectorField(double xCoordinate, double yCoordinate, double xConstant, double yConstant) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.xConstant = xConstant;
		this.yConstant = yConstant;
		degree = 2;
	}

	public EllipticalRepulsionVectorField() {

	}

	private Vector horizontalVector(RobotPosition rp) {
		UnitVector uv = new UnitVector(Math.abs(rp.getX() - xCoordinate), Math.abs(rp.getY() - yCoordinate));
		return new Vector(uv.getX(), 0).multiply(xConstant * Math.abs(Math.pow(xCoordinate - rp.getX(), degree)));
	}

	private Vector verticalVector(RobotPosition rp) {
		UnitVector uv = new UnitVector(Math.abs(rp.getX() - xCoordinate), Math.abs(rp.getY() - yCoordinate));
		return new Vector(uv.getY(), 0).multiply(yConstant * Math.abs(Math.pow(yCoordinate - rp.getX(), degree)));
	}

	@Override
	public Vector apply(RobotPosition rp) {
		return horizontalVector(rp).add(verticalVector(rp));
	}

}
