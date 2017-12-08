package org.usfirst.frc.team2854.map;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SensorBoard;

public class AccelerometerBased implements MapInput {

	private SensorBoard sensors;
	private long startTime = 0, lastTime = 0;
	private double deltaTime = 0;
	
	private double velocity;
	
	
	public AccelerometerBased() {
		sensors = Robot.getSensors();
	} 
	
	@Override
	public double getDeltaForward() {
		return velocity * deltaTime;
	}

	@Override
	public double getPosError() {
		return Math.pow(sensors.getForwardAccel().getError(), 3);
	}

	@Override
	public double getRotation() {
		return sensors.getGyroValue();
	}

	@Override
	public void update() {
		startTime = System.nanoTime();
		deltaTime = (startTime - lastTime)/1E9d;
		
		velocity += sensors.getForwardAccelValue() * deltaTime;
	
		lastTime = startTime;
	}

}
