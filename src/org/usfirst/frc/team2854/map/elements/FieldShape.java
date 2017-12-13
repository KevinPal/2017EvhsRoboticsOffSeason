package org.usfirst.frc.team2854.map.elements;

import org.usfirst.frc.team2854.map.math.RobotPosition;
import org.usfirst.frc.team2854.map.elements.MapDrawable;

public interface FieldShape extends MapDrawable{
	
	public boolean isWithinBounds(RobotPosition rp);

}
