package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunShooterMotors extends Command {
	//double throttleValue = Robot.oi.copilotJoystick.getThrottle();
	
	double throttle;
	
	public RunShooterMotors() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
     //this.throttle = SmartDashboard.getNumber("Throttle Value Pilot Joystick", .55);
    	
    }

    // Called repeatedly when this Command is scheduled to run
  
	protected void execute() {
 /*   	Robot.robotShooter.runShooterMotors(0.55);//0.78511
*/    	//Robot.robotShooter.runShooterMotors(throttle);
		Robot.robotShooter.runShooterMotors(Robot.oi.pilotJoystick.getThrottle());

		//SmartDashboard.putNumber("Throttle Value ", throttleValue);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.robotShooter.shooterOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
