package org.usfirst.frc.team4188.robot.subsystems;



import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.DoNothingRelay;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Relays extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Relay randomRelay = RobotMap.randomRelay;

	public void init(){
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new DoNothingRelay());
    	
    }
    
    public void randomRelayForward(){
    	randomRelay.set(Relay.Value.kForward);
    }
    
    public void randomRelayReverse(){
    	randomRelay.set(Relay.Value.kReverse);
    }
    
    public void randomRelayOn(){
    	randomRelay.set(Relay.Value.kOn);
    	
    }
    
    public void randomRelayOff(){
    	randomRelay.set(Relay.Value.kOff);
    }
    
    
    
}

