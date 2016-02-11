package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.DoNothingRetriever;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Retriever extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	private static final double PNEUMATIC_DELAY_SECONDS = 0.5;


	DoubleSolenoid retrieverSolenoidRight = RobotMap.retrieverDoubleSolenoidRight;
	DoubleSolenoid retrieverSolenoidLeft = RobotMap.retrieverDoubleSolenoidLeft;
	Relay retrieverRelayOuter = RobotMap.retrieverRelayOuter;
	Relay retrieverRelayInner = RobotMap.retrieverRelayInner;
	Compressor retrieverCompressor = RobotMap.compressor;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        
    
    }
    

    public void init(){
    	retrieverRelayOuter.set(Relay.Value.kOff);
    	retrieverCompressor.start();
    	doNothing();
    }
    public void retrieveBall(){
    	retrieverRelayOuter.set(Relay.Value.kForward);
    	
    }
    
    
    public void ejectBall(){
    	retrieverRelayOuter.set(Relay.Value.kReverse);
    }
    
    public void deployRetriever(){
    	retrieverSolenoidRight.set(DoubleSolenoid.Value.kForward);
    	retrieverSolenoidLeft.set(DoubleSolenoid.Value.kForward);
   
    }
    
    public void retractRetriever(){
    	retrieverSolenoidRight.set(DoubleSolenoid.Value.kReverse);
    	retrieverSolenoidLeft.set(DoubleSolenoid.Value.kReverse);
    	
    }
    
    public void doNothing (){
    	retrieverRelayOuter.set(Relay.Value.kOff);
    }
    
    public void doNothingRetrieverSolenoid(){
    	retrieverSolenoidRight.set(DoubleSolenoid.Value.kOff);
    	retrieverSolenoidLeft.set(DoubleSolenoid.Value.kOff);
    	
    }
    
}

