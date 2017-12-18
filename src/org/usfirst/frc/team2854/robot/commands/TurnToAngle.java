package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SensorBoard;
import org.usfirst.frc.team2854.robot.SubsystemNames;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command {

	DriveTrain drive;
	private double targetAngle, startingAngle, lastAngle, totalAngle, angleDiff;
	private SensorBoard sensors;
	
    public TurnToAngle(double angle) {
        requires(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
        this.targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	drive = (DriveTrain) Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN);
    	sensors = Robot.getSensors();
    	startingAngle = sensors.getNavX().getAngle();
    	lastAngle = sensors.getNavX().getAngle();
    	totalAngle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	startingAngle = sensors.getNavX().getAngle();
    	totalAngle += (startingAngle - lastAngle);
    	drive.drive(-.2, .2);
    	angleDiff = totalAngle - targetAngle;
    	
    	SmartDashboard.putNumber("Total Angle", totalAngle);
    	SmartDashboard.putNumber("TargetAngle", targetAngle);
    	SmartDashboard.putNumber("AngleDiff", angleDiff);

    	lastAngle = startingAngle;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(angleDiff)%360 < .5f;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Ending");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
