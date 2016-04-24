package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveWithGyro extends Command {
	ADXRS450_Gyro gyro = RobotMap.driveTrainGyro;
	Timer timer;
	boolean isTimerStartedYet;
	boolean doneYet;
	
	double timerValue;
	double throttle;
	
    public AutoDriveWithGyro(double throttle, double timerValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.throttle = throttle;
    	this.timerValue = timerValue;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	gyro.reset();
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
    			double correction = gyro.getAngle()/10;
    			correction = correction > 1 ? 1: correction;
    			SmartDashboard.putNumber("Gyro Correction", correction);
    			Robot.drivetrain.autoDrive(throttle, correction); //negative means it goes right    		
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
