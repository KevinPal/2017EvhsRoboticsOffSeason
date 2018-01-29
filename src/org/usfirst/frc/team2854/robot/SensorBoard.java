package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.map.input.DualSensor;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

public class SensorBoard {

	private AHRS navX;
	private ADXRS450_Gyro spiGyro;
	private BuiltInAccelerometer builtInacc;
	private TalonSRX l1, l2, r1, r2;
	private double zeroLeft;
	private double zeroRight;
	private DualSensor gyro;
	private DualSensor forwardAccel;

	public SensorBoard() {
		try {
			navX = new AHRS(SerialPort.Port.kMXP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("--------------" + navX.isConnected() + "----------------");
		try {
			spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			builtInacc = new BuiltInAccelerometer();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			gyro = new DualSensor("Gyro");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		forwardAccel = new DualSensor("Forward Acceleration");
	}

	public void calibrate(long time) {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < time) {
			gyro.addValue(spiGyro.getRate(), navX.getRate());
			forwardAccel.addValue(builtInacc.getX(), navX.getRawAccelY());
		}
		gyro.calibrate();
		forwardAccel.calibrate();
	}

	public double getGyroValue() {
		return gyro.calculateValue(spiGyro.getRate(), navX.getRate());
	}

	public double getForwardAccelValue() {
		return forwardAccel.calculateValue(builtInacc.getX(), navX.getRawAccelX());
	}

	public DualSensor getGyro() {
		return gyro;
	}

	public DualSensor getForwardAccel() {
		return forwardAccel;
	}

	public BuiltInAccelerometer getAccelerometer() {
		return builtInacc;
	}

	public AHRS getNavX() {
		return navX;
	}

	public double getLeftEncoder() {
		return (l1.getSelectedSensorPosition(0) + l2.getSelectedSensorPosition(0)) / 2 - zeroLeft;
	}

	public double getRightEncoder() {
		return (r1.getSelectedSensorPosition(0) + r2.getSelectedSensorPosition(0)) / 2 - zeroRight;
	}

	public void zeroEncoders() {
		zeroLeft = getLeftEncoder();
		zeroRight = getRightEncoder();
	}
}
