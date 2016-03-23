package org.usfirst.frc.team4188.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Erin King
 */
public class CHSRobotDrive extends RobotDrive implements PIDOutput {
    //static final int kFrontLeft_val = 0;
    //static final int kFrontRight_val = 1;
    //static final int kRearLeft_val = 2;
    //static final int kRearRight_val = 3;
    //static final double minValue = 0.17;
    
    public RobotDrive robotDrive1, robotDrive2, robotDrive3;
    
    /*public CHSRobotDrive(final int leftMotorChannel, final int rightMotorChannel) {
        super(leftMotorChannel, rightMotorChannel);
    }

    public CHSRobotDrive(final int frontLeftMotor, final int rearLeftMotor,
            final int frontRightMotor, final int rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    public CHSRobotDrive(SpeedController leftMotor, SpeedController rightMotor) {
        super(leftMotor, rightMotor);
    } */

    public CHSRobotDrive(SpeedController leftMotor1, SpeedController rightMotor1,
            SpeedController leftMotor2, SpeedController rightMotor2, 
            SpeedController leftMotor3, SpeedController rightMotor3) {
        
    	super(leftMotor1, rightMotor1);
    	robotDrive1 = new RobotDrive(leftMotor1, rightMotor1);
        robotDrive2 = new RobotDrive(leftMotor1, rightMotor1); 
        //this is for the middle motors
        robotDrive3 = new RobotDrive(leftMotor1, rightMotor1); 
    }

       
    public void pidWrite(double output){
    	
    	//SmartDashboard.putNumber("Left Motor", super.m_rearLeftMotor.get());
    	//SmartDashboard.putNumber("Right Motor", super.m_rearRightMotor.get());
    	
    	robotDrive1.setLeftRightMotorOutputs(output,output);
    	robotDrive2.setLeftRightMotorOutputs(output,output);
    	robotDrive3.setLeftRightMotorOutputs(-output,-output);
    }
    
  /*  private int getInverted(SpeedController motor){
    	if(motor.getInverted())
    		return -1;
    	else
    		return 1;
    	
    } */
    //comment to test GIT
}