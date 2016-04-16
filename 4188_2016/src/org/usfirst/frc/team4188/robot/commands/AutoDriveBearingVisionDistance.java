package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.CHSLog;
import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveBearingVisionDistance extends Command {
	boolean isDistanceSatisfiedYet;
	double currentDistance;
	double moveDirection;
	double rotation;
	

    public AutoDriveBearingVisionDistance(double moveValue, double rotateValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	moveDirection = moveValue;
    	rotation = rotateValue;
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	isDistanceSatisfiedYet = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    currentDistance = Robot.getDistance();
    if(!Double.isNaN(currentDistance)){
    		while(currentDistance > 8.3){
    			Robot.drivetrain.autoDrive(moveDirection, rotation);
    		}
    		Robot.drivetrain.autoDrive(0, 0);
    		isDistanceSatisfiedYet = true;
    	}
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDistanceSatisfiedYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        isDistanceSatisfiedYet = false;
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
	}
}