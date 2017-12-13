package org.usfirst.frc.team2854.map.input;

import java.text.MessageFormat.Field;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SensorBoard;

public class EncoderBased implements MapInput {
	private double deltaX;
	private double deltaY;
	private double deltaTheta;
	private SensorBoard s;

	public EncoderBased() {
		s = Robot.getSensors();
	}

	@Override
	public double getDeltaForward() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPosError() {
		// TODO Do I account slipping in this?`
		return 0;
	}

	@Override
	public double getRotation() {
		return deltaTheta;
	}

	@Override
	public void update() {
		double left = s.getLeftEncoder();
		double right = s.getRightEncoder();
		try {
			double r = Math.abs((dt.getWidth() / (right - left)) * (right + left) / 2);
			double theta = (right + left) / 2;
			deltaX = (r-r  * Math.cos(theta);
			deltaY = (r * Math.sin(theta);
		} catch (ArithmeticException e) {
			double distance = (left + right) / 2;
			deltaX = distance * Math.cos(Robot.fm.getRobotPosition().getTheta());
			deltaY = distance * Math.sin(Robot.fm.getRobotPosition().getTheta());
		}
		dt.zeroEncoders();
	}

}
