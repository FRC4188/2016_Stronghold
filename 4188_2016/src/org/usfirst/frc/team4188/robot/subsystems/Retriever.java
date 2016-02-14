package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.DoNothingRetriever;

import edu.wpi.first.wpilibj.CANTalon;
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
	CANTalon retrieverTalonOuter = RobotMap.retrieverTalonOuter;
	CANTalon retrieverTalonInner = RobotMap.retrieverTalonInner;
	Compressor retrieverCompressor = RobotMap.compressor;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        
    
    }
    

    public void init(){
    	retrieverTalonOuter.set(0);
    	retrieverTalonInner.set(0);
    	retrieverCompressor.start();
    	doNothing();
    }
    public void retrieveBall(){
    	retrieverTalonOuter.set(0.5);
    	retrieverTalonInner.set(0.5);
    	
    }
    
    
    public void ejectBall(){
    	retrieverTalonOuter.set(-0.5);
    	retrieverTalonInner.set(-0.5);
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
    	retrieverTalonOuter.set(0);
    	retrieverTalonInner.set(0);
    }
    
    public void doNothingRetrieverSolenoid(){
    	retrieverSolenoidRight.set(DoubleSolenoid.Value.kOff);
    	retrieverSolenoidLeft.set(DoubleSolenoid.Value.kOff);
    	
    }
    
}

