package org.usfirst.frc.team2854.map;

public class Vector {

	private double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector muliply(Matrix m) {
		double[][] mat = m.getMat();
		double nX = mat[0][0] * x + mat[0][1] * y;
		double nY = mat[1][0] * x + mat[1][1] * y;
		
		return new Vector(nX, nY);
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

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}
	
	
}
