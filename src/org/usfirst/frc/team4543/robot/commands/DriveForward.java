package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class DriveForward extends TimedCommand {

	private DriveTrain driveTrain;
	
    public DriveForward(double timeout) {
        super(timeout);
        requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain = (DriveTrain) Robot.getSubSystem(Subsystems.DRIVE_TRAIN);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	driveTrain.drive(.2, .2);
    }
     

    // Called once after timeout
    protected void end() {
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveTrain.stop();
    }
}
