package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Relay scalerRelayRight = RobotMap.scalerRelayRight;
	Relay scalerRelayLeft = RobotMap.scalerRelayLeft;
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public void scalerRelaysUp()
	{
		scalerRelayRight.set(Relay.Value.kForward);
		scalerRelayLeft.set(Relay.Value.kReverse);
	}

	public void scalerRelaysDown()
	{
		scalerRelayRight.set(Relay.Value.kReverse);
		scalerRelayLeft.set(Relay.Value.kReverse);
	}
	
	public void scalerRelaysOff()
	{
		scalerRelayRight.set(Relay.Value.kOff);
		scalerRelayLeft.set(Relay.Value.kOff);
	}
}

