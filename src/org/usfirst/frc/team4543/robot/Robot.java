
package org.usfirst.frc.team4543.robot;

import java.util.HashMap;

import org.usfirst.frc.team4543.robot.commands.map.UpdateRobotAngleAndRobotPositionEncoder;
import org.usfirst.frc.team4543.robot.commands.map.UpdateRobotPositionEncoder;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;
import org.usfirst.team4543.map.FieldMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;
	Command urpe;
	SendableChooser<Command> chooser = new SendableChooser<>();
	public static FieldMap fm;
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */

	public static AHRS ahrs;
	static HashMap<Subsystems, Subsystem> subsystems = new HashMap<Subsystems, Subsystem>();
	private static double fieldWidth;// TODO decide the unit of this, looks like inches
	private static double fieldHeight;// TODO decide the unit of this looks like inches
	private static double startingX = 0;
	private static double startingY = 0;

	@Override
	public void robotInit() {
		ahrs = new AHRS(SerialPort.Port.kMXP);

		fm = new FieldMap(fieldWidth, fieldHeight);
		fm.setRobotPosition(startingX, startingY);
		fm.setTargetPosition(startingX, startingY, false);
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		subsystems.put(Subsystems.DRIVE_TRAIN, new DriveTrain());
		subsystems.put(Subsystems.FIELD_MAP, fm);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		urpe = new UpdateRobotPositionEncoder();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	private class Periodic extends CommandGroup {
		public Periodic() {
			addParallel(new UpdateRobotAngleAndRobotPositionEncoder());
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().add(new Periodic());
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public static Subsystem getSubSystem(Subsystems system) {
		return subsystems.get(system);
	}
}
