package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**Command will require DriveTrain which takes control away form any other command
 * that may be using it. This command will immediately exit making the DriveTrain available 
 * to any other subsystem.
 *
 *This command is intended to be used a general purpose way to cancel any command that may be
 *using the DriveTrain such as a runaway command.
 */
public class SeizeDriveTrain extends Command {

    public SeizeDriveTrain() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
