package com.team2854.mapauto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.NetPermission;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.team2854.builtincommands.DriveCommand;
import com.team2854.builtincommands.TurnCommand;
import com.team2854.fieldAuto.FieldAuto;
import com.team2854.fieldAuto.Path;
import com.team2854.fieldAuto.Waypoint;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommand extends CommandGroup {

	public AutoCommand(TurnCommand turnCommand, DriveCommand driveCommand, FieldAuto field) {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		ArrayList<Waypoint> waypoints = field.getP().getPath();
		Waypoint point;
		double currentAngle = 90;
		for (int k = 0; k < waypoints.size()-1; k++) {
			point = waypoints.get(k);
			if (!point.getCommand().equals("")) {
				Command cmd = generateCommand(point.getCommand(), field.getTeamNum());
				if(point.isParallel()) {
					addParallel(cmd);
				} else {
					addSequential(cmd);
				}
			}
			
			Waypoint nextPoint = waypoints.get(k+1);
			double targetAngle = Math.toDegrees(Math.atan2(point.getY()-nextPoint.getY(), - point.getX() + nextPoint.getX()));
			double deltaAngle = targetAngle - currentAngle;
			turnCommand.setAngle(deltaAngle);
			addSequential(turnCommand);
			double targetDistance = Math.sqrt(Math.pow(point.getY()-nextPoint.getY(), 2) + Math.pow(point.getX()-nextPoint.getX(), 2));
			driveCommand.setDistance(targetDistance);
			addSequential(driveCommand);
		}
		point = waypoints.get(waypoints.size()-1);
		if (!point.getCommand().equals("")) {
			Command cmd = generateCommand(point.getCommand(), field.getTeamNum());
			if(point.isParallel()) {
				addParallel(cmd);
			} else {
				addSequential(cmd);
			}
		}
	}
	
	public Command generateCommand(String signiture, int teamNum) {
		String str = signiture;
		if (!(str.contains("(") && str.contains(")"))) {
			throw new RuntimeException("Invalid command given, " + str
					+ " please give the command in proper java syntax, like myCommand(5, 10)");
		}
		String cmd = str.split("(")[0];
		String strParams = str.split("(")[1].split(")")[0];
		String[] strParamArray = strParams.split(",");
		int paramsLen = strParams.trim().length() == 0 ? 0 : strParams.split(",").length + 1;
		Object[] params = new Object[paramsLen];
		Class<?>[] paramTypes = new Class[paramsLen];
		for (int j = 0; j < paramsLen; j++) {
			Double d;
			Integer i;
			try {
				d = Double.valueOf(strParamArray[j].trim());
				i = Integer.valueOf(strParamArray[j].trim());
			} catch (NumberFormatException e) {
				params[j] = strParamArray[j].trim();
				paramTypes[j] = String.class;
				continue;
			}
			if (i.intValue() == d.doubleValue()) {
				params[j] = i.intValue();
				paramTypes[j] = int.class;
			} else {
				params[j] = d.doubleValue();
				paramTypes[j] = double.class;
			}
			
		}
		
		
		
		try {
			Class<Command> c = (Class<Command>) Class.forName("org.usfirst.frc.team" + teamNum + ".robot.commands." + cmd.trim());
			Constructor<Command> constructor = c.getDeclaredConstructor(paramTypes);
			Command command = constructor.newInstance(params);	
			return command;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
