package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.ShooterDoNothing;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon shooterMotor = RobotMap.shooter;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new ShooterDoNothing());
    	
    	
    }
    
    public void runShooterMotorWithThrottleForward(double throttle){
    	shooterMotor.set(1*throttle);
    	
    }
    public void runShooterMotorWithThrottleBackward(double throttle){
    	shooterMotor.set(-1*throttle);
    }

	public void shooterOff() {
		shooterMotor.set(0);
	}


}

