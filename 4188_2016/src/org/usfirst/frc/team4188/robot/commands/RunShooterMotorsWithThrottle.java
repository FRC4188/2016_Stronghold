package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunShooterMotorsWithThrottle extends Command {

    double throttle;
	
    public RunShooterMotorsWithThrottle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//throttle = SmartDashboard.getNumber("Enter Shooter Speed", 0.2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.robotShooter.runShooterMotors(Robot.oi.copilotJoystick.getThrottle());
    	//Robot.robotShooter.runShooterMotors(0.482);
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
