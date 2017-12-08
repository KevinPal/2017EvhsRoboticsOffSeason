package org.usfirst.frc.team2854.map;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class FieldMap implements Runnable{

	private int fWidth, fHeight, sWidth, sHeight;
	private CvSink input;
	private CvSource output;
	private MapInput robotInput;
	public volatile boolean shouldRun = true;
	
	
	
	public FieldMap(int fWidth, int fHeight, int sWidth, int sHeight, MapInput robotInput) {
		this.fWidth = fWidth;
		this.fHeight = fHeight;
		this.sWidth = sWidth;
		this.sHeight = sHeight;
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		input = CameraServer.getInstance().getVideo();
		output = CameraServer.getInstance().putVideo("Map", sWidth, sHeight);
		new Thread(this).start();
	} 
	
	

	@Override
	public void run() {
		long startTime = System.nanoTime();
		long lastTime = System.nanoTime();
		double deltaTime = 0;
		Vector robotPos =  new Vector(0, 0);
		while(shouldRun) {
			startTime = System.nanoTime();
			deltaTime = (startTime - lastTime)/1E9d;
			robotInput.update();
			Mat screen = new Mat(sHeight, sWidth, CvType.CV_8UC1);
			Imgproc.cvtColor(screen, screen, Imgproc.COLOR_RGB2GRAY);
			robotPos.add(robotInput.getDeltaForward() * Math.cos(robotInput.getRotation()), robotInput.getDeltaForward() * Math.sin(robotInput.getRotation()));
			Vector screenPos = new Vector(((robotPos.getX()/(double)(fWidth)*2)-1)*sWidth/2d + sWidth/2d,
										((robotPos.getY()/(double)(fHeight)*2)-1)*sHeight/2d + sHeight/2d);
			for(int i = 0; i < sWidth; i++) {
				for(int j = 0; j < sHeight; j++) {
					if(i == (int)screenPos.getX() && j == (int)screenPos.getY()) {
						screen.put(j, i, new byte[] {(byte) 0x00});
					} else {
						screen.put(j, i, new byte[] {(byte) 0xff});
					}

				}
			}
			
			output.putFrame(screen);
			lastTime = startTime;
		}
	}

}
