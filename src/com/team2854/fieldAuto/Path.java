package com.team2854.fieldAuto;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Path implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3168693082526162095L;

	private ArrayList<Waypoint> path;
	
	private static transient BufferedImage wayPointIcon;
	private static transient BufferedImage wayPointSelectedIcon;

	public Path() {
		path = new ArrayList<Waypoint>();
		File f = new File("res/waypoint.png");
		if(!f.exists()) {
			System.err.println("The file was not found at "  + f.getAbsolutePath());
		}
		try {
			wayPointIcon = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File f2 = new File("res/selected.png");
		if(!f.exists()) {
			System.err.println("The file was not found at "  + f2.getAbsolutePath());
		}
		try {
			wayPointSelectedIcon = ImageIO.read(f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addPoint(Waypoint p) {
		path.add(p);
	}
	
	public void drawPaths(Graphics g) {
		
		for(int i = 1; i < path.size(); i++) {
			g.drawLine(path.get(i-1).getX(), path.get(i-1).getY(), path.get(i).getX(), path.get(i).getY());
		}
		
	}

	public static BufferedImage getWayPointIcon() {
		return wayPointIcon;
	}

	public ArrayList<Waypoint> getPath() {
		return path;
	}

	public static BufferedImage getWayPointSelectedIcon() {
		return wayPointSelectedIcon;
	}

	public void setPath(ArrayList<Waypoint> path) {
		this.path = path;
	}
	
	
}
