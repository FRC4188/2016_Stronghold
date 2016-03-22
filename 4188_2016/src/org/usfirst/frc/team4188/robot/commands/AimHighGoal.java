package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 */
public class AimHighGoal extends Command {

    public AimHighGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
  
			
    		
    		//Grab Image
    		//Robot.robotVision.getRawImage();
        	//Process Image to find target center
    	
        	
    		//Get size based off target image and target size
        	//Calculate Robot Rotation Angle
        	//Trigger PID Loop to move robot to specified rotation angle
        	//Shoot
    		
    
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
