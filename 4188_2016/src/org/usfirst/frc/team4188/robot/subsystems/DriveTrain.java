
package org.usfirst.frc.team4188.robot.subsystems;


import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.ManualDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	// DAR Test.

    DoubleSolenoid gearShift = RobotMap.gearShift;
    RobotDrive robotDrive = RobotMap.driveBase;
    RobotDrive robotDriveMiddle = RobotMap.driveBaseMiddle;
	CANTalon frontLeft = RobotMap.frontLeft;
	CANTalon frontRight = RobotMap.frontRight;
	CANTalon rearLeft = RobotMap.rearLeft;
	CANTalon rearRight = RobotMap.rearRight;
	CANTalon middleLeft = RobotMap.middleLeft;
	CANTalon middleRight = RobotMap.middleRight;
	AnalogGyro gyro = RobotMap.driveTrainGyro;
	
	static final double TICK_DISTANCE = RobotMap.TICKS_PER_INCH;
	
	public void init(){
		gyro.reset();
		robotDrive.setSafetyEnabled(false);
		robotDriveMiddle.setSafetyEnabled(false);
	}

	 
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ManualDrive());
	}
	
	public void driveWithJoystick(double y, double x, double throttle){
        robotDrive.arcadeDrive(y*throttle, x*throttle);
        robotDriveMiddle.arcadeDrive(-y*throttle, -x*throttle);
        
    }
	
	
	
	
	public double getEncoderFR(){
		return frontRight.getEncPosition();
	}
	
	public double getEncoderFL(){
		return frontLeft.getEncPosition();
	}
	
	public double getEncoderRR(){
		return rearRight.getEncPosition();
	}
	
	public double getEncoderRL(){
		return rearLeft.getEncPosition();
	}
	
	public double getEncoderML(){
		return middleLeft.getEncPosition();
	}
	public double getEncoderMR(){
		return middleRight.getEncPosition();
	}
	
	public void resetEncoders() { 
        frontLeft.setPosition(0);
        frontRight.setPosition(0);
        rearLeft.setPosition(0);
        rearRight.setPosition(0);
        middleLeft.setPosition(0);
        middleRight.setPosition(0);
	}
	
	public double getDistance(){	// Returns value in inches
		double frontLeft = Robot.drivetrain.getEncoderFL();
		double frontRight = -Robot.drivetrain.getEncoderFR();
		double rearLeft = Robot.drivetrain.getEncoderRL();
		double rearRight = -Robot.drivetrain.getEncoderRR();
		double middleLeft = Robot.drivetrain.getEncoderML();
		double middleRight = Robot.drivetrain.getEncoderMR();
        return ((frontLeft + frontRight + rearLeft + rearRight + middleLeft + middleRight) / 4.0) / TICK_DISTANCE;
	}
	
	public void getEncoderValues(){        
        SmartDashboard.putNumber("frontLeftEncoder distance", frontLeft.getEncPosition());
        SmartDashboard.putNumber("frontRightEncoder distance", frontRight.getEncPosition());
        SmartDashboard.putNumber("rearLeftEncoder distance", rearLeft.getEncPosition());
        SmartDashboard.putNumber("rearRightEncoder distance", rearRight.getEncPosition());
	}

	public void autoDrive(double moveValue, double rotateValue){
		robotDrive.arcadeDrive(moveValue, rotateValue);
		robotDriveMiddle.arcadeDrive(-moveValue, -rotateValue);
	}
	
	public void gradualAccelerate(){
		this.setRampRate(1.2);
	}
	public void setRampRate(double rampRate) {
		frontLeft.setVoltageRampRate(rampRate);
		frontRight.setVoltageRampRate(rampRate);
		rearLeft.setVoltageRampRate(rampRate);
		rearRight.setVoltageRampRate(rampRate);
		middleLeft.setVoltageRampRate(rampRate);
		middleRight.setVoltageRampRate(rampRate);
	}
	
	public void shiftGearForward(){
		gearShift.set(DoubleSolenoid.Value.kForward);
	}
	public void shiftGearBackward(){
		gearShift.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void gearShiftDoNothing(){
		gearShift.set(DoubleSolenoid.Value.kOff);
	}
}
    
    


