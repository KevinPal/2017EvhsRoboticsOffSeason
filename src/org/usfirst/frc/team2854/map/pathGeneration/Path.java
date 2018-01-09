package org.usfirst.frc.team2854.map.pathGeneration;

import java.util.ArrayList;

import org.usfirst.frc.team2854.map.math.RobotPosition;

public class Path {
	private ArrayList<RobotPosition> alrp;

	// TODO Decide whether or not to make this work with the api, or just do it as
	// simple as possible
	public Path() {
		alrp = new ArrayList<RobotPosition>(2);
	}

	public Path(int minimumWaypoints) {
		if (minimumWaypoints >= 2) {
			alrp = new ArrayList<RobotPosition>(minimumWaypoints);
		} else {
			System.out.println("You must have at least 2 waypoints");
			alrp = new ArrayList<RobotPosition>(2);
		}

	}

	public RobotPosition getRobotPosition(int index) {
		return alrp.get(index);
	}

	public ArrayList<RobotPosition> getArrayList() {
		return alrp;
	}
}
