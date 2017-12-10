package org.usfirst.team4543.map;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FieldMap extends Subsystem {
	private RobotPosition rPos;
	private RobotPosition targetPos;
	private Field f;

	public FieldMap(double width, double height) {
		f = new Field(width, height);
		rPos = new RobotPosition();
		targetPos = new RobotPosition();
	}

	public Field getField() {
		return f;
	}

	public RobotPosition getRobotPosition() {
		return rPos;
	}

	public RobotPosition getTargetPosition() {
		return targetPos;
	}

	public void setRobotPosition(double x, double y) {
		if (rPos.equals(null)) {
			rPos = new RobotPosition(x, y);
		} else {
			rPos.setLocation(x, y);
		}
	}

	public void setRobotAngle(double angle) {
		rPos.setTheta(angle);
	}

	public boolean setTargetPosition(double x, double y, boolean force) {
		RobotPosition check = new RobotPosition(x, y);
		System.out.print(f.isWithinBounds(new RobotPosition(x, y))
				? ("Warning: The targetPosition is out of the field boundaries"
						+ ((force) ? ", \n\tProceeding anyways\n" : ""))
				: "");
		if ((!f.isWithinBounds(check) && force) || f.isWithinBounds(check)) {
			targetPos = check;
			return true;
		}
		return false;
	}

	public void setTargetAngle(double angle) {
		targetPos.setTheta(angle);
	}

	public void setField(double width, double height) {
		if (f.equals(null)) {
			this.f = new Field(width, height);
		} else {
			f.setSize(width, height);
		}
	}

	public void setField(Field f) {
		this.f = f;
	}

	@Override
	protected void initDefaultCommand() {
	}
}
