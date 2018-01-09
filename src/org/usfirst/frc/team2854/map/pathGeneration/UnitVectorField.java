
package org.usfirst.frc.team2854.map.pathGeneration;

import java.util.function.Function;

import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.math.Vector;

public class UnitVectorField implements Function<RobotPosition, Vector> {
	/**
	 * @param rp
	 *            RobotPosition
	 * @return a vector that corresponds to the RobotPosition rp
	 */
	@Override
	public Vector apply(RobotPosition rp) {
		return super.apply()/super.apply().length();
	}

}
