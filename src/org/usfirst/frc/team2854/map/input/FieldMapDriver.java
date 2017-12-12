package org.usfirst.frc.team2854.map.input;

import java.awt.Color;
import java.io.ObjectInputStream.GetField;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.usfirst.frc.team2854.map.elements.FieldMap;
import org.usfirst.frc.team2854.map.math.Vector;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FieldMapDriver implements Runnable{

	private double fWidth, fHeight;
	private int sWidth, sHeight;
	private CvSink input;
	private CvSource output;
	private MapInput robotInput;
	public volatile boolean shouldRun = true;
	
	private FieldMap map;
	
	public FieldMapDriver(FieldMap map, int sWidth, int sHeight, MapInput robotInput) {
		this.fWidth = map.getField().getWidth();
		this.fHeight = map.getField().getHeight();
		this.sWidth = sWidth;
		this.sHeight = sHeight;
		map = new FieldMap(fWidth, fHeight);
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		input = CameraServer.getInstance().getVideo();
		output = CameraServer.getInstance().putVideo("Map", sWidth, sHeight);
		this.robotInput = robotInput;
		new Thread(this).start();
	} 
	
	

	@Override
	public void run() {
		long startTime = System.nanoTime();
		long lastTime = System.nanoTime();
		double deltaTime = 0;
		while(shouldRun) {
			startTime = System.nanoTime();
			deltaTime = (startTime - lastTime)/1E9d;
			robotInput.update();
			Mat screen = new Mat(sHeight, sWidth, CvType.CV_8UC3);
			
			for(int i = 0; i < sWidth; i++) {
				for(int j = 0; j < sHeight; j++) {
					
					screen.put(j, i, new byte[] {(byte) 0xff});	
				}
			}
			
			map.getField().draw(screen, new Vector(0,0), Color.black);
			
			
			output.putFrame(screen);
			lastTime = startTime;
		}
		System.out.println("Exiting Field Map");
	}

}
