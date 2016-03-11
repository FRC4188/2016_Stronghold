package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShoot extends Command {
	
	Timer timer;
	boolean isTimerStartedYet;
	boolean doneYet;
	
	double shooterSpeed;

    public AutoShoot(double shooterSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	
    	this.shooterSpeed= shooterSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
    		if(timer.get() < 0.4){
    			Robot.robotRetriever.ejectBall(0.7);
    			
    		}
    		if(timer.get()> 0.4 && timer.get()<2){
    			Robot.robotRetriever.ejectBall(0);
    			Robot.robotShooter.runShooterMotors(this.shooterSpeed);
    		}
    		if(timer.get()> 2 && timer.get()<0.4){
    			Robot.robotRetriever.retrieveBall(1);
    		}
    		else{
    			Robot.robotRetriever.doNothing();
    			Robot.robotShooter.shooterOff();
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
    	timer.stop();
    	timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
