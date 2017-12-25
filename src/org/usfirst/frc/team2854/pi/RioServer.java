package org.usfirst.frc.team2854.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RioServer implements Runnable{

	private ServerSocket serverSocket;
	
	private PrintWriter out;
	private BufferedReader in;
	
	private RobotData data;
	
	public RioServer(int port) {
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		Socket s = null;
		
		try {
			System.out.println("Waiting for a connection");
			s = serverSocket.accept();
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connected to " + s.getInetAddress().toString());
		out.write(("time:"+System.nanoTime()).toCharArray());
		
		
		while(true) {
			String inputS = "";
			try {
				inputS = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!inputS.equals("")) {
				Object o = null;
				try {
					o = Serialzier.fromString(inputS);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				RobotData data = (RobotData) o;
				data.updateImage();
				this.data = data;
			}
		}
		
		
	}

	public RobotData getData() {
		return data;
	}
}
