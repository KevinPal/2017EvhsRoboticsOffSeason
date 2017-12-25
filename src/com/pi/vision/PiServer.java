package com.pi.vision;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import edu.wpi.cscore.CvSink;
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

		PiConnection connection = null;
		
		try {
			connection = new PiConnection("10.45.43.101", 27); //randomly using port 27 might be bad idea
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		new Thread(connection).start();
		
		
		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
		//UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture();
		
		CvSink sink = new CvSink("cam");
		sink.setSource(cam1);
		
		while(true) { 
			//StereoSGBM s = StereoSGBM.create(minDisparity, numDisparities, blockSize, P1, P2, disp12MaxDiff, preFilterCap, uniquenessRatio, speckleWindowSize, speckleRange, mode) //StereoSGBM apprenety gives better quality @ cost of speed (vs StereoBM)
			Mat m = new Mat();
			sink.grabFrame(m);
			RobotData data = new RobotData(m);
					
			connection.writeData(data);
			
		}
		
	}
	
	
	
}
