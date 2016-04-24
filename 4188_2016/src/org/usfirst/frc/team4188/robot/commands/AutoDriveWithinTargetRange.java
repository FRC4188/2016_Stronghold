package org.usfirst.frc.team4188.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4188.robot.CHSLog;
import org.usfirst.frc.team4188.robot.Robot;

/**
 *
 */
public class AutoDriveWithinTargetRange extends Command {

    double speed;
	public AutoDriveWithinTargetRange(double speed) {		
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.drivetrain.autoDrive(speed, 0.0);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return (Robot.robotVision.numParticles > 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.autoDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
	}
}
