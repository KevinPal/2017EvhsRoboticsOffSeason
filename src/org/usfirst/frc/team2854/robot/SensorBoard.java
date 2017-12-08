package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.map.DualSensor;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

public class SensorBoard {

	private AHRS navX;
	private ADXRS450_Gyro spiGyro;
	private BuiltInAccelerometer accelerometer;
	
	private DualSensor gyro;
	private DualSensor forwardAccel;
	
	public SensorBoard() {
		navX = new AHRS(SerialPort.Port.kMXP);
		spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
		accelerometer = new BuiltInAccelerometer();
		gyro = new DualSensor("Gyro");
		forwardAccel = new DualSensor("Forward Acceleration");
	}
	
	public void calibrate(long time) {
		long startTime = System.nanoTime();
		while(System.nanoTime() - startTime < time) {
			gyro.addValue(spiGyro.getRate(), navX.getRate());
			forwardAccel.addValue(accelerometer.getX(), navX.getRawAccelX());
		}
		gyro.calibrate();
		forwardAccel.calibrate();
	}

	public double getGyroValue() {
		return gyro.calculateValue(spiGyro.getRate(), navX.getRate());
	}

	public double getForwardAccelValue() {
		return forwardAccel.calculateValue(accelerometer.getX(), navX.getRawAccelX());
	}

	public DualSensor getGyro() {
		return gyro;
	}

	public DualSensor getForwardAccel() {
		return forwardAccel;
	}
	
	
}
