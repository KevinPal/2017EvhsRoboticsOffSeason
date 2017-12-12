
package org.usfirst.frc.team4543.map;

public class Vector {

	private double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector v) {
		add(v.x, v.y);
	}

	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

	public static double dot(Vector a, Vector b) {
		return a.getX() * b.getX() + a.getY() * b.getY();
	}

}