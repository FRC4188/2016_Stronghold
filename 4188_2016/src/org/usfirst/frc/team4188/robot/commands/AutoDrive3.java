package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.CHSLog;
import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive3 extends Command {
	private static final double kP = 0.3;
	Timer timer;
	boolean isTimerStartedYet;
	boolean doneYet;
	
	double timerValue;
	double magnitude;
	double curve;
	
	

    public AutoDrive3(double magnitude, double timerValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.magnitude = magnitude;
    	this.timerValue = timerValue;
    	
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	RobotMap.driveTrainGyro.reset();
    	timer = new Timer();
    	isTimerStartedYet = false;
    	doneYet = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(!isTimerStartedYet) {
			timer.start();
			isTimerStartedYet = true;
		}
    	
    	else{
    		if(timer.get() < this.timerValue) {
    		curve = RobotMap.driveTrainGyro.getAngle();
    		Robot.drivetrain.autoDriveBearingAngle(this.magnitude, curve*kP);
    		
    		}
    		else{
    			Robot.drivetrain.autoDrive(0, 0);
    			doneYet = true;
    		}
    	}
    }
    	
    	
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        doneYet = false;
        isTimerStartedYet = false;
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
	}
}