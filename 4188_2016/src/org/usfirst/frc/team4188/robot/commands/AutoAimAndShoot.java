package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAimAndShoot extends CommandGroup {
    
    public  AutoAimAndShoot() {
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
    	requires(Robot.drivetrain);
    	
    	addSequential(new CameraLightsOn());
    	addSequential(new DelayWhileCheckingForTargets());
    	addSequential(new AutoDriveBearingVisionDistance(12, 0.5));
    	addSequential(new AimHighGoal(3.0));
    	addSequential(new AutoDriveBearingVisionDistance(10, 0.5));
        addSequential(new AimHighGoal(1.0));
        addSequential(new AimHighGoal(0.5));
        addSequential(new AutoShoot3());
    	addSequential(new CameraLightsOff());
        Robot.drivetrain.setRampRate(1023);
    }
}
