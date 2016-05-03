package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	CANTalon scalerTalonRight = RobotMap.scalerTalonRight;
	CANTalon scalerTalonLeft = RobotMap.scalerTalonLeft;
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public void scalerTalonUp(double throttle)
	{
		scalerTalonLeft.set(throttle);
	}

	public void scalerTalonDown(double throttle)
	{
		scalerTalonLeft.set(-throttle);
	}
	
	public void scalerTalonsOff()
	{
		scalerTalonRight.set(0);
		scalerTalonLeft.set(0);
	}
	
	public void armUp(){
		scalerTalonRight.set(-1);
	}
	
	public void armDown(){
		scalerTalonRight.set(1);
	}
}

