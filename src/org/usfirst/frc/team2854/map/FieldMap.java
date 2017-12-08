package org.usfirst.frc.team2854.map;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		this.robotInput = robotInput;
		new Thread(this).start();
	} 
	
	

	@Override
	public void run() {
		long startTime = System.nanoTime();
		long lastTime = System.nanoTime();
		double deltaTime = 0;
		Vector robotPos =  new Vector(0, 150);
		double x = 0;
		System.out.println("Running field map");
		while(shouldRun) {
			startTime = System.nanoTime();
			deltaTime = (startTime - lastTime)/1E9d;
			robotInput.update();
			Mat screen = new Mat(sHeight, sWidth, CvType.CV_8UC1);
			//robotPos.add(robotInput.getDeltaForward() ,0);
			//robotPos = new Vector(robotPos.getX() + robotInput.getDeltaForward(), 0);
			x += robotInput.getDeltaForward();
			Vector screenPos = new Vector(((x/(double)(fWidth)*2)-1)*sWidth/2d + sWidth/2d,
										((robotPos.getY()/(double)(fHeight)*2)-1)*sHeight/2d + sHeight/2d);
			
			//System.out.println("Robot Pos: " + robotPos + " Screen Pos: " + screenPos);
			SmartDashboard.putString("Robot Position", x + "");
			for(int i = 0; i < sWidth; i++) {
				for(int j = 0; j < sHeight; j++) {
					
					screen.put(j, i, new byte[] {(byte) 0xff});
				}
			}
			screen.put((int)screenPos.getY(),(int) screenPos.getX(), new byte[] {(byte) 0x00});
			screen.put((int)screenPos.getY()+1,(int) screenPos.getX(), new byte[] {(byte) 0x00});
			screen.put((int)screenPos.getY()-1,(int) screenPos.getX(), new byte[] {(byte) 0x00});
			screen.put((int)screenPos.getY(),(int) screenPos.getX()+1, new byte[] {(byte) 0x00});
			screen.put((int)screenPos.getY(),(int) screenPos.getX()-1, new byte[] {(byte) 0x00});


			
			
			output.putFrame(screen);
			lastTime = startTime;
		}
		System.out.println("Exiting Field Map");
	}

}
