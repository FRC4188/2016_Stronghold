package org.usfirst.frc.team4188.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAutonomous extends CommandGroup {
    
    public  LowBarAutonomous() {
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
    	addSequential(new ShiftDriveGearForward()); //Shifts to low gear
    	addSequential(new RetrieverOut()); //Lowers Retriever for low bar
    	addSequential(new AutoDrive(0.65,0,4.65)); //Drives Forward 4.5 sec
    	addParallel(new AutoDrive(0,0,1));//Stop 1 sec.
    	//addSequential(new EjectBallFullSpeed(),1); //Eject Ball for 1 sec
    	addSequential(new AutoDrive(-0.65,0,4.65)); //Drive Backwards through defense
    	addSequential(new AutoDrive(0,0,0.5));//stop
    	addSequential(new AutoDrive(0.65, 0 , 4.65));
    	addSequential(new AutoDrive(0,0,0.5));
    	
    	
    }
}
