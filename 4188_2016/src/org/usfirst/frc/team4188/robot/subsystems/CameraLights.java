package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraLights extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Relay cameraRelay = RobotMap.cameraLightRelay;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
 
	
	
	

	public void init(){
		
	}
    
    public void cameraRelayForward(){
    	cameraRelay.set(Relay.Value.kForward);
    }
    
    public void cameraRelayReverse(){
    	cameraRelay.set(Relay.Value.kReverse);
    }
    
    public void cameraRelayOn(){
    	cameraRelay.set(Relay.Value.kOn);
    	cameraRelay.set(Relay.Value.kForward);
    	
    }
    
    public void cameraRelayOff(){
    	cameraRelay.set(Relay.Value.kOff);
    }
}

