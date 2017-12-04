package org.usfirst.team4543.map;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FieldMap extends Subsystem {
	private RobotPosition rPos;
	private RobotPosition targetPos;
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

	public FieldMap(double width, double height) {
		f = new Field(width, height);
		rPos = new RobotPosition();
		targetPos = new RobotPosition();
	}

	public FieldMap(Field f, RobotPosition rPos) {
		this.f = (Field) f.clone();
		this.rPos = (RobotPosition) rPos.clone();
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

	public double getTargetX() {
		return targetPos.getX();
	}

	public double getTargetY() {
		return targetPos.getY();
	}

	public void setRobotPosition(double x, double y) {
		if (rPos.equals(null)) {
			rPos = new RobotPosition(x, y);
		} else {
			rPos.setLocation(x, y);
		}
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
