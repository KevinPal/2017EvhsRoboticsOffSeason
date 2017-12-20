package com.pi.vision;

import org.opencv.calib3d.StereoBM;
import org.opencv.calib3d.StereoSGBM;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class PiServer extends Thread{
	


	public static void main(String[] args) {
		
		new PiServer().run();
		
	}
	
	
	@Override
	public void run() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("uh somehow get ip of robot"); //TODO GET robot ip
		NetworkTable table = NetworkTable.getTable("visionTable");
		NetworkTable status = NetworkTable.getTable("robotStatus");
		table.putString("testKey", "testValue");
		
		while(!status.getBoolean("isRobotInit") || !status.getBoolean("visionInit")) { //wait for robot init
			Thread.sleep(1000);
		}
		
		
		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
		UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture();
		
		
		
		while(true) { 
			StereoSGBM s = StereoSGBM.create(minDisparity, numDisparities, blockSize, P1, P2, disp12MaxDiff, preFilterCap, uniquenessRatio, speckleWindowSize, speckleRange, mode) //StereoSGBM apprenety gives better quality @ cost of speed (vs StereoBM)
			
		}
		
	}
	
	
	
}
