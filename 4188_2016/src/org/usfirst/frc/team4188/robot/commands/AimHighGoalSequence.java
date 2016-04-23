package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AimHighGoalSequence extends CommandGroup {
    
    public  AimHighGoalSequence() {
    	double finalTolerance = 1.0;
    	double angle;
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new AimHighGoal(1.0));
    	angle = Robot.getAimError();
    	if (angle >= finalTolerance) {
        	addSequential(new AimHighGoal(1.0));
    	}
    	angle = Robot.getAimError();
    	if (angle >= finalTolerance) {
        	addSequential(new AimHighGoal(1.0));
    	}
    }
}
