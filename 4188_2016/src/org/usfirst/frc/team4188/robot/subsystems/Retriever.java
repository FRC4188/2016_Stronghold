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

	
	DoubleSolenoid retrieverSolenoid = RobotMap.retrieverDoubleSolenoid;
	Relay retrieverRelay = RobotMap.retrieverRelay;
	Compressor retrieverCompressor = RobotMap.compressor;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothingRetriever());
    
    }
    

    public void init(){
    	retrieverRelay.set(Relay.Value.kOff);
    	retrieverCompressor.start();
    	doNothing();
    }
    public void retrieveBall(){
    	retrieverRelay.set(Relay.Value.kForward);
    	
    }
    
    public void ejectBall(){
    	retrieverRelay.set(Relay.Value.kReverse);
    }
    
    public void deployRetriever(){
    	retrieverSolenoid.set(DoubleSolenoid.Value.kForward);
    	Timer.delay(PNEUMATIC_DELAY_SECONDS);
    	retrieverSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void retractRetriever(){
    	retrieverSolenoid.set(DoubleSolenoid.Value.kReverse);
    	Timer.delay(PNEUMATIC_DELAY_SECONDS);
    	retrieverSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void doNothing (){
    	retrieverRelay.set(Relay.Value.kOff);
    }
    
}

