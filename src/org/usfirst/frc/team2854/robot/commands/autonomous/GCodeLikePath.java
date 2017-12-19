package org.usfirst.frc.team2854.robot.commands.autonomous;

import java.util.ArrayList;
import java.util.Iterator;

import org.usfirst.frc.team2854.map.math.RobotPosition;

public class GCodeLikePath implements Path {
	private ArrayList<Double> xCoordinates;
	private ArrayList<Double> yCoordinates;
	private Iterator<Double> x;
	private Iterator<Double> y;
	private int counter;

	public GCodeLikePath() {
		xCoordinates = new ArrayList<Double>();
		yCoordinates = new ArrayList<Double>();
		x = xCoordinates.iterator();
		y = yCoordinates.iterator();
		
	}

	public GCodeLikePath(ArrayList<Double> x, ArrayList<Double> y) {
		if (x.size() == y.size()) {
			xCoordinates = x;
			yCoordinates = y;
		} else {
			System.out.println("The arrayLists do not match");
		}
	}

	public RobotPosition next() {
		return new RobotPosition();
	}
}
