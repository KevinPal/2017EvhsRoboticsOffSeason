package com.pi.vision;

import java.awt.Frame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.VideoInputFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

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
	
		table.putString("testKey", "testValue");
		
		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
		UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture();
		
		while(true) { 
			
		}
		
	}
	
	
	
}
