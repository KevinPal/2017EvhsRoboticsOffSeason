package org.usfirst.frc.team2854.map.pathGeneration;

import java.util.function.Function;

import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.math.Vector;
import org.usfirst.frc.team2854.robot.Robot;

public class VectorField implements Function<RobotPosition, Vector> {
	/**
	 * @param rp
	 *            RobotPosition
	 * @return a vector that corresponds to the RobotPosition rp
	 */
	@Override
	BoundaryRepulsionVectorField brvf = new BoundaryRepulsionVectorField(Robot.f,1);
	public Vector apply(RobotPosition rp) {
		return	Vector.sum();
	}

}
