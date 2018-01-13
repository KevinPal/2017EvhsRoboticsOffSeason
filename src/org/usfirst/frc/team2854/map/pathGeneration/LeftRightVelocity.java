package org.usfirst.frc.team2854.map.pathGeneration;

import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.math.Vector;

public class LeftRightVelocity {
	private Vector targetVector;
	private Vector currentVector;

	public LeftRightVelocity(RobotPosition rp, Vector[] v) {
		targetVector = Vector.sum(v);
		// currentVector = new Vector(rp.getSpeed() * Math.cos(rp.getTheta()),
		// rp.getSpeed() * Math.sin(rp.getTheta()));//FIXME

		// FIXME wait til Palani adds a velocity thingy for the RobotPosition
	}

	private double velocityDifference(Vector targetVector, Vector currentVector) {
		return (targetVector.getTheta() - currentVector.getTheta()) / 180;// positive is right side greater
	}

	private double averageVelocity() {
		return targetVector.getR() - Math.abs(velocityDifference(targetVector, currentVector) / 2);
	}

	public double getLeft() {
		return averageVelocity() - velocityDifference(targetVector, currentVector);

	}

	public double getRight() {
		return averageVelocity() + velocityDifference(targetVector, currentVector) / 2;
	}
}
