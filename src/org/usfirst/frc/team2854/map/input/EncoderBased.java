package org.usfirst.frc.team2854.map.input;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SensorBoard;
import org.usfirst.frc.team2854.robot.SubsystemNames;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

public class EncoderBased implements MapInputCartesian {
	private double deltaX;
	private double deltaY;
	private double deltaTheta;
	private double theta;// TODO Idk if this class should be keeping track of this. I just put it here to
							// put something for the MapInput Interface

	private SensorBoard s;
	private double width;

	public EncoderBased() {
		s = Robot.getSensors();
		width = ((DriveTrain) (Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN))).getWidth();
	}

	@Override
	public double getDeltaForward() {
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

	@Override
	public double getDeltaX() {
		return deltaX;
	}

	@Override
	public double getDeltaY() {
		return deltaY;
	}

	@Override

	public double getRotation() {
		theta += deltaTheta;
		return theta;
	}

	@Override
	public double getDeltaRotation() {
		return deltaTheta;
	}

	@Override
	public double getPosError() {
		return -1;
	}

	@Override
	public void update() {
		double left = s.getLeftEncoder();
		double right = s.getRightEncoder();
		try {
			double r = Math.abs((width / (right - left)) * (right + left) / 2);
			double theta = (right - left) / width;
			deltaX = (r - r * Math.cos(theta));
			deltaY = (r * Math.sin(theta));
		} catch (ArithmeticException e) {
			double distance = (left + right) / 2;
			deltaX = distance * Math.cos(Robot.fm.getRobotPosition().getTheta());
			deltaY = distance * Math.sin(Robot.fm.getRobotPosition().getTheta());
		}
		s.zeroEncoders();
	}

}
