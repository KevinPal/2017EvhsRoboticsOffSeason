package org.usfirst.frc.team2854.map.input;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SensorBoard;
import org.usfirst.frc.team2854.robot.SubsystemNames;

public class EncoderBased implements MapInput {
	private double deltaX;
	private double deltaY;
	private double deltaTheta;
	private SensorBoard s;
	private double width;

	public EncoderBased() {
		s = Robot.getSensors();
		width = (DriveTrain) (Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
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
