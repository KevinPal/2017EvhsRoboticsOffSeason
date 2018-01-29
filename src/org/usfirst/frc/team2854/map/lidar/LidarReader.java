package org.usfirst.frc.team2854.map.lidar;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.SerialPort;

public class LidarReader implements Runnable {
	private PacketSeperator ps;
	private SerialPort lidar;
	private byte[] rawData;

	private byte[] headerSignature = { (byte) 0xFA };

	private double[] distances = new double[360];

	private int dataSize;

	private int dataBit = 8;

	public LidarReader(SerialPort.Port port, int baudRate) {
		dataSize = 1446;
		lidar = new SerialPort(baudRate, port, dataBit);
		rawData = new byte[dataSize];
		ps = new PacketSeperator(rawData, new byte[] { (byte) 0xFA });
	}

	public LidarReader(SerialPort.Port port) {
		dataSize = 1446;
		lidar = new SerialPort(115200, port, dataBit);
	}

	private boolean readRawData() {
		try {
			rawData = new byte[dataSize];
			rawData = lidar.read(dataSize);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean processData() {
		if (readRawData()) {
			ps = new PacketSeperator(rawData, headerSignature);
			for (Packet p : ps.getArrayListPacket()) {
				for (int j = 0; j < 4; j++) {
					try {
						distances[p.getDegree() + j] = p.getDataPointArray()[j].getDistance();
					} catch (Exception e) {
						System.out.println("wut...");
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	Mat lidarGui;
	int width = 800, height = 800;

	public Mat getMat() {
		int x0 = width / 2, y0 = height / 2;
		lidarGui = Mat.zeros(height, width, 0);
		for (int i = 0; i < getDistances().length; i++) {
			int x, y;
			x = (int) (Math.cos(i * Math.PI / 180.0) * getDistances()[i]) + x0;
			y = (int) (Math.sin(i * Math.PI / 180.0) * getDistances()[i]) + y0;

			if (x >= 0 && x <= width && y >= 0 && y <= height) {
				try {
					lidarGui.put(y, x, 255);
				} catch (Exception e) {
					System.out.println("Rip mat.put");
				}
			}
		}
		return lidarGui;
	}

	public double[] getDistances() {
		return distances;
	}

	@Override
	public void run() {
		processData();
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	public PacketSeperator getPacketSeperator() {
		return ps;
	}

	public void free() {
		lidar.free();
	}

}
