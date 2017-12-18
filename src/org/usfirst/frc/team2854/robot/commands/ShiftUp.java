package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.SubsystemNames;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftUp extends Command {

	DriveTrain drive;
	
    public ShiftUp() {
        //requires(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive = (DriveTrain) Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN);
    	drive.shiftUp();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Shift up is being interupted");
    }
}
