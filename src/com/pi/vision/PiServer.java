package com.pi.vision;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class PiServer extends Thread{
	


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		for(String s:System.getProperty("java.library.path").split(";")) {
			System.out.println(s);
		}
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat m = new Mat(2, 2, CvType.CV_64FC3);
		
		for(int i = 0; i < m.height(); i++) {
			for(int j = 0; j < m.width(); j++) {
				m.put(i, j, Math.random(), Math.random(), Math.random());
			}
		}
		RobotData data = new RobotData(m);
		System.out.println(data);
		data.updateImgData();
		long startTime = System.nanoTime();
		String s = Serialzier.toString(data);
		System.out.println("Time taken: " + (System.nanoTime() - startTime)/1E9d);
		System.out.println(s);
		Object o = Serialzier.fromString(s);
		RobotData serializedData = (RobotData) o;
		serializedData.updateImage();
		System.out.println(serializedData);
		//new PiServer().run();
		
	}
	
	
	@Override
	public void run() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.45.43.2"); //TODO GET robot ip
		NetworkTable table = NetworkTable.getTable("visionTable");
		NetworkTable status = NetworkTable.getTable("robotStatus");
		table.putString("testKey", "testValue");
		
		//while(!status.getBoolean("isRobotInit") || !status.getBoolean("visionInit")) { //wait for robot init
		//	Thread.sleep(1000);
		//}
		
		
		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
		//UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture();
		
		
		
		while(true) { 
			//StereoSGBM s = StereoSGBM.create(minDisparity, numDisparities, blockSize, P1, P2, disp12MaxDiff, preFilterCap, uniquenessRatio, speckleWindowSize, speckleRange, mode) //StereoSGBM apprenety gives better quality @ cost of speed (vs StereoBM)
			
		}
		
	}
	
	
	
}
