package org.usfirst.team4543.map;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FieldMap extends Subsystem {
	private RobotPosition rPos;

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
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public double getHeight() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setSize(double width, double height) {
			// TODO Auto-generated method stub

		}

	}

	private class RobotPosition extends Point2D {
		private double x;
		private double y;

		private RobotPosition(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public double getX() {
			// TODO Auto-generated method stub
			return x;
		}

		@Override
		public double getY() {
			// TODO Auto-generated method stub
			return y;
		}

		@Override
		public void setLocation(double x, double y) {
			this.x = x;
			this.x = y;
		}

	}

	public FieldMap(double width, double height, double x, double y) {
		f = new Field(x, y);
		rPos = new RobotPosition(0, 0);
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

	public void setRobotPosition(double x, double y) {

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
