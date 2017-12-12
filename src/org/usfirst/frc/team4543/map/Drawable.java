package org.usfirst.frc.team4543.map;

import java.awt.Color;

import org.opencv.core.Mat;

public interface Drawable {

	public void draw(Mat m, Vector translation, Color c);
	
}
