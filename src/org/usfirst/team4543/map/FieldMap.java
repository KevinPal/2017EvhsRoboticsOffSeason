package org.usfirst.team4543.map;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FieldMap extends Subsystem {
	private RobotPosition rPos;
	private RobotAngle rAngle;
	private RobotPosition targetPos;
	private RobotAngle targetAngle;
	private Field f;

	private class Field extends Dimension2D {
		private double width;
		private double height;

		public Field(double width, double height) {
			this.width = width;
			this.height = height;
		}

		@Override
		public double getWidth() {
			return width;
		}

		@Override
		public double getHeight() {
			return height;
		}

		@Override
		public void setSize(double width, double height) {
			this.width = width;
			this.height = height;
		}

	}

	private class RobotPosition extends Point2D {
		private double x;
		private double y;

		private RobotPosition(double x, double y) {
			this.x = x;
			this.y = y;
		}

		private RobotPosition() {
			x = 0;
			y = 0;
		}

		@Override
		public double getX() {
			return x;
		}

		@Override
		public double getY() {
			return y;
		}

		@Override
		public void setLocation(double x, double y) {
			this.x = x;
			this.x = y;
		}

	}

	private class RobotAngle {
		private double angle;

		public RobotAngle() {
			angle = 0;
		}

		public RobotAngle(double angle) {
			this.angle = angle;
		}

		public void setAngle(double angle) {
			this.angle = angle;
		}

		public double getAngle() {
			return angle;
		}

		public Object clone() {
			return this.clone();
		}
	}

	public FieldMap(double width, double height) {
		f = new Field(width, height);
		rPos = new RobotPosition();
		targetPos = new RobotPosition();
		rAngle = new RobotAngle();
		targetAngle = new RobotAngle();
	}

	public FieldMap(Field f, RobotPosition rPos, RobotAngle rAngle) {
		this.f = (Field) f.clone();
		this.rPos = (RobotPosition) rPos.clone();
		this.rAngle = (RobotAngle) rAngle.clone();
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

	public double getHeight() {
		return f.getHeight();
	}

	public double getWidth() {
		return f.getWidth();
	}

	public double getX() {
		return rPos.getX();
	}

	public double getY() {
		return rPos.getY();
	}

	public double getTheta() {
		return rAngle.getAngle();
	}

	public double getTargetX() {
		return targetPos.getX();
	}

	public double getTargetY() {
		return targetPos.getY();
	}

	public double getTargetTheta() {
		return targetAngle.getAngle();
	}

	public void setRobotPosition(double x, double y) {
		if (rPos.equals(null)) {
			rPos = new RobotPosition(x, y);
		} else {
			rPos.setLocation(x, y);
		}
	}

	public void setRobotAngle(double angle) {
		rAngle.setAngle(angle);
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
		targetAngle.setAngle(angle);
	}

	public void setField(double width, double height) {
		if (f.equals(null)) {
			this.f = new Field(width, height);
		} else {
			f.setSize(width, height);
		}
	}

	@Override
	protected void initDefaultCommand() {
	}
}
