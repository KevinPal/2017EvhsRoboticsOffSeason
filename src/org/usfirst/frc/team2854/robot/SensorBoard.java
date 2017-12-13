package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.map.input.DualSensor;
import com.ctre.CANTalon;

public class SensorBoard {

	private AHRS navX;
	private ADXRS450_Gyro spiGyro;
	private BuiltInAccelerometer builtInacc;
	private CANTalon l1;
	private CANTalon l2;
	private CANTalon r1;
	private CANTalon r2;
	private double zeroLeft;
	private double zeroRight;
	private DualSensor gyro;
	private DualSensor forwardAccel;

	public SensorBoard() {
		navX = new AHRS(SerialPort.Port.kMXP);
		System.out.println("--------------" + navX.isConnected() + "----------------");
		spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS1);
		builtInacc = new BuiltInAccelerometer();
		gyro = new DualSensor("Gyro");
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
		return (l1.getEncPosition() + l2.getEncPosition()) / 2 - zeroLeft;
	}

	public double getRightEncoder() {
		return (r1.getEncPosition() + r2.getEncPosition()) / 2 - zeroRight;
	}

}
