/**
 * 
 */
package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EndAimHighGoal {

	/**
	 * 
	 */
	public EndAimHighGoal() {
		
	}
	
	 protected void initialize() {  
	
		 PIDController gyroPIDController = RobotMap.gyroPIDController;
		 gyroPIDController.disable();
	    	}	
	    
	    

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	//Grab Image
	    	//Process Image to find target center 
	   	    //Get size based off target image and target size
	    	//Calculate Robot Rotation Angle
	    	//Trigger PID Loop to move robot to specified rotation angle
	    	//Shoot
	    	//new AutoShoot2();
	    	SmartDashboard.putString("Aim Status", "Ending");
	   }

	    // Make this return true when this Command no longer needs to run execute()
	    
	    protected boolean isFinished() {
			return true;
	    	
	    }
	    // Called once after isFinished returns true
	    protected void end(){

	        SmartDashboard.putString("Aim Status", "PID loop ended successfully");
	      
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	end();
	    }

}
