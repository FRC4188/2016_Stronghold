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
    	addSequential(new ShiftDriveGearForward());
    	addSequential(new ScalerDownFullSpeed(),0.35);
    	addSequential(new RetrieverOut(),1);
    	addSequential(new AutoDrive(0.75,0,4.65)); //Drives Forward 4.5 sec
    	addParallel(new AutoDrive(0,0,0.2));//Stop 1 sec.
    	//addSequential(new EjectBallFullSpeed(),1); //Eject Ball for 1 sec
    	addSequential(new AutoDrive(-0.75,0,4.65)); //Drive Backwards through defense
    	addSequential(new AutoDrive(0,0,0.2));//stop
    	addSequential(new AutoDrive(0.75, 0 , 4.65)); //Drive Back Forwards
    	addSequential(new AutoDrive(0,0,0.2));
    	
    	
    }
}
