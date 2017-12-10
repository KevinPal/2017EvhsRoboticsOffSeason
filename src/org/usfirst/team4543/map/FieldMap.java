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
		System.out.print((x < 0 || y < 0 || x > f.getWidth() || y > f.getHeight())
				? ("Warning: The targetPosition is out of the field boundaries"
						+ ((force) ? ", \n\tProceeding anyways\n" : ""))
				: "");
		if (((x < 0 || y < 0 || x > f.getWidth() || y > f.getHeight()) && force)
				|| !(x < 0 || y < 0 || x > f.getWidth() || y > f.getHeight())) {
			if (targetPos.equals(null)) {
				targetPos = new RobotPosition(x, y);
			} else {
				targetPos.setLocation(x, y);
			}
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

	}

	@Override
	protected void initDefaultCommand() {
	}
}
