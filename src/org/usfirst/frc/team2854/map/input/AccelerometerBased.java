package org.usfirst.frc.team2854.map.input;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SensorBoard;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AccelerometerBased implements MapInput {

	private SensorBoard sensors;
	private long startTime = 0, lastTime = 0;
	private double deltaTime = 0;
	private boolean init = true;
	private double velocity = 0;

	public AccelerometerBased() {
		sensors = Robot.getSensors();
	}

	@Override
	public double getDeltaForward() {
		// return Robot.getSensors().getNavX().getVelocityY() * deltaTime;
		return velocity * deltaTime;
	}

	@Override
	public double getPosError() {
		return Math.pow(sensors.getForwardAccel().getError(), 3);
	}

	@Override
	public double getRotation() {
		return 0;
		// return sensors.getGyroValue();
	}

	@Override
	public void update() {
		if (init) {
			init();
			init = false;
		}
		startTime = System.nanoTime();// TODO it looks like the timer class
										// references like an actual legit
										// seperate hardware Timer, so Imma take
										// a guess and say it's better
		deltaTime = (startTime - lastTime) / 1E9d;
		SmartDashboard.putNumber("NavX Vel X", sensors.getNavX().getVelocityX());
		SmartDashboard.putNumber("NavX Vel Y", sensors.getNavX().getVelocityY());
		SmartDashboard.putNumber("NavX Vel Z", sensors.getNavX().getVelocityZ());

		SmartDashboard.putNumber("NavX Acc X", sensors.getNavX().getRawAccelX());
		SmartDashboard.putNumber("NavX Acc Y", sensors.getNavX().getRawAccelY());
		SmartDashboard.putNumber("NavX Acc Z", sensors.getNavX().getRawAccelZ());

		SmartDashboard.putNumber("NavX Acc World X", sensors.getNavX().getWorldLinearAccelX());
		SmartDashboard.putNumber("NavX Acc World Y", sensors.getNavX().getWorldLinearAccelY());
		SmartDashboard.putNumber("NavX Acc World Z", sensors.getNavX().getWorldLinearAccelZ());

		SmartDashboard.putBoolean("Is Connected", sensors.getNavX().isConnected());
		SmartDashboard.putBoolean("Is Callibrating", sensors.getNavX().isCalibrating());

		velocity += sensors.getNavX().getWorldLinearAccelY() * deltaTime * 10;

		SmartDashboard.putNumber("Velocity", velocity);

		// System.out.println("Acc: " + sensors.getAccelerometer().getX()*10 + "
		// Velocity: " + velocity);
		// velocity += sensors.getForwardAccelValue() * 10 * deltaTime;

		lastTime = startTime;
	}

	public void init() {
		startTime = System.nanoTime();
		lastTime = System.nanoTime();
	}

}
