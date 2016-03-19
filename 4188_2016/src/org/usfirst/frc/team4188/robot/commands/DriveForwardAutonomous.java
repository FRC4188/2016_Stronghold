package org.usfirst.frc.team4188.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForwardAutonomous extends CommandGroup {
  Timer timer = new Timer();
    public  DriveForwardAutonomous() {
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
    	//addSequential(new ScalerUpFullSpeed(),0.5);
    	addSequential(new RetrieverOut(),2);
    	addSequential(new AutoDrive(0.65,0,4.65));
    	addSequential(new AutoDrive(0,0,1));
    	
    }
    

}
