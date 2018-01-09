package org.usfirst.frc.team2854.map.pathGeneration;

import java.util.function.Function;

import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.math.Vector;

public class VectorField implements Function<RobotPosition, Vector> {
	/**
	 * @param rp
	 *            RobotPosition
	 * @return a vector that corresponds to the RobotPosition rp
	 */
	@Override
	public Vector apply(RobotPosition rp) {
		return new Vector(0, 0);// This should return a vector depending on the robot position I'll do the math on this later
	}

}
