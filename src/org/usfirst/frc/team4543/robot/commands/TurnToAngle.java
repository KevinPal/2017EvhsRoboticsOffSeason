package org.usfirst.frc.team4543.robot.commands;

import org.usfirst.frc.team4543.robot.Robot;
import org.usfirst.frc.team4543.robot.Subsystems;
import org.usfirst.frc.team4543.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command {

	private DriveTrain driveTrain;
	private double startingAngle, targetAngle, currentAngle;
	
    public TurnToAngle(double angle) {
        requires(Robot.getSubSystem(Subsystems.DRIVE_TRAIN));
        targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain = (DriveTrain) Robot.getSubSystem(Subsystems.DRIVE_TRAIN);
    	startingAngle = Robot.ahrs.getAngle();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.ahrs.getAngle();
    	double deltaAngle = currentAngle - startingAngle;
    	double speed = (1 - deltaAngle / targetAngle + .4)/2f;
    	if(targetAngle < 0) {
    		driveTrain.drive(-speed, speed);
    	} else {
    		driveTrain.drive(speed, -speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	currentAngle = Robot.ahrs.getAngle();
    	double deltaAngle = currentAngle - startingAngle;
    	double speed = (1 - deltaAngle / targetAngle + .4)/2f;
    	return speed < .25;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveTrain.stop();
    }
}
