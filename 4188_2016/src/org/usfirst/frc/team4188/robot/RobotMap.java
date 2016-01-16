package org.usfirst.frc.team4188.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Relay;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    public static final double TICKS_PER_INCH = 45;
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
public static CHSRobotDrive driveBase;
public static CANTalon frontLeft;
public static CANTalon frontRight;
public static CANTalon rearLeft;
public static CANTalon rearRight;

public static AnalogGyro driveTrainGyro;

public static Relay cameraLightRelay;
public static void init(){
	
	driveTrainGyro = new AnalogGyro(0);
	
	frontLeft = new CANTalon(0);
	frontRight = new CANTalon(1);
	rearLeft = new CANTalon(2);
	rearRight = new CANTalon(3);
	
	frontLeft.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	frontRight.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	rearLeft.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	rearRight.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	
	
	driveBase = new CHSRobotDrive (frontLeft, rearLeft, frontRight, rearRight);
	driveBase.setSafetyEnabled(false);
	driveBase.setExpiration(0.1);
	driveBase.setSensitivity(0.5);
	driveBase.setMaxOutput(1.0);
	
	
}


}
