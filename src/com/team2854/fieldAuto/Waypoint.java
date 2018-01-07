package com.team2854.fieldAuto;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Waypoint extends JButton implements MouseListener, MouseMotionListener {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4477766185125112220L;

	private transient int draggedAtX, draggedAtY;

	private String command = "";
	private boolean isParallel = false;

	private static int counter = 0;
	private int id;

	public Waypoint(int x, int y) {
		super(new ImageIcon(Path.getWayPointIcon()));
		id = counter++;
		BufferedImage img = Path.getWayPointIcon();
		this.setLocation(x - img.getWidth() / 2, y - img.getHeight());
		this.setSize(img.getWidth(), img.getHeight());

		System.out.println("Creating waypoint with id " + id);
		setBorderPainted(false);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public int getX() {
		return getLocation().x + Path.getWayPointIcon().getWidth() / 2;
	}

	public int getY() {
		return getLocation().y + Path.getWayPointIcon().getHeight() / 2;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	public String getCommand() {
		return command;
	}

	public boolean isParallel() {
		return isParallel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Waypoint other = (Waypoint) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setParallel(boolean isParallel) {
		this.isParallel = isParallel;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLocation(int x, int y) {
		super.setLocation(x - Path.getWayPointIcon().getWidth() / 2, y - Path.getWayPointIcon().getHeight() / 2);
	}

}
