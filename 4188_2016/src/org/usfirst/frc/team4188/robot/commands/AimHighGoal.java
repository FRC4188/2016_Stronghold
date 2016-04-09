package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AimHighGoal extends Command {
	
    public AimHighGoal() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.drivetrain);
         setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.gyroReset();
    	Robot.drivetrain.goToAngle(90);
     
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
    	if(Robot.drivetrain.gyroPIDController.onTarget()==true){
    		end();
    	}	
    	
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//tell the command when the PID controller is on target
    	return Robot.drivetrain.gyroPIDController.onTarget();
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.gyroPIDController.disable();
    	RobotMap.gyroPIDController.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    end();
    }
    
}
