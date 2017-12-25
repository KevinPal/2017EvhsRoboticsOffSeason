package com.pi.vision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

public class PiConnection implements Runnable {

	private String ip;
	private int port;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;

	private long timeOffset;

	private volatile RobotData dataToWrite;
	private Semaphore lock;

	public PiConnection(String ip, int port) throws UnknownHostException, IOException {
		this.ip = ip;
		this.port = port;
		s = new Socket(ip, port);
		System.out.println("Connected to " + s.getInetAddress().toString());
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		// wait for a message in the format time:123 and where 123 is the server time in
		// nano seconds
		String s = in.readLine();
		try {
			timeOffset = System.nanoTime() - Long.valueOf(s.split(":")[1]);
		} catch (Exception e) {
			System.out.println(
					"Expecting a message in the format time:123, without quotes and where 123 is the server time in nano seconds");
		}
		lock = new Semaphore(1);
	}

	public void writeData(RobotData d) {
		
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (lock) {
			this.dataToWrite = d;
		}
		
	}
	//all this synch stuff is to make sure value of dataToWrite doesnt change half way thru sending it
	@Override
	public void run() {
		if (dataToWrite != null) {
			RobotData safeData = dataToWrite; 
			lock.release();
			safeData.setTimeStamp(System.nanoTime() - timeOffset);
			String s = null;
			try {
				s = Serialzier.toString(safeData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.write(s.toCharArray());
			dataToWrite =  null;

		}
	}

}
