package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AimHighGoal extends Command {

	public PIDController gyroPIDController = RobotMap.gyroPIDController;
	
	 
	private static final double KP = 0.1;
	private static final double KI = 0.0;
	private static final double KD = 0.0;
	//private static final double KF = 6.0;
	/**
	 double KP = SmartDashboard.getNumber("Kp value");
	 double KI = SmartDashboard.getNumber("Ki value");
	 double KD = SmartDashboard.getNumber("Kd value");
	 double KF = SmartDashboard.getNumber("Kf value");
	**/
	 
	private double angle ;
	
	
    public AimHighGoal() {
        // Use requires() here to declare subsystem dependencies 
    	requires(Robot.drivetrain);
    	//this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {  
    	SmartDashboard.getNumber("KP", KP);
    	SmartDashboard.getNumber("KI", KI);
    	SmartDashboard.getNumber("KD", KD);
    	SmartDashboard.putString("Aim Status", "Initializing");

/*
gyroPIDController = new PIDController(KP, KI, KD, KF, RobotMap.driveTrainGyro, RobotMap.driveBase);
*/
Robot.drivetrain.gyroReset();    	
gyroPIDController = new PIDController(KP, KI, KD, RobotMap.driveTrainGyro, RobotMap.driveBase);
    	new CameraLightsOff();
    	angle = Robot.getAimError();
//    	angle = 90;
    	
    	if(!Double.isNaN(angle)){
    		Robot.drivetrain.gyroReset();
	    	//Robot.drivetrain.goToAngle(90);
    		//RobotMap.driveTrainGyro.reset();
    		gyroPIDController.setAbsoluteTolerance(2);
    		gyroPIDController.setSetpoint(angle);
            //if(!gyroPIDController.isEnabled()); gyroPIDController.enable();
    		gyroPIDController.enable();
    	}	

    }
    
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Grab Image
    	//Process Image to find target center 
   	    //Get size based off target image and target size
    	//Calculate Robot Rotation Angle
    	//Trigger PID Loop to move robot to specified rotation angle
    	//Shoot
    	//new AutoShoot2();
    	SmartDashboard.putString("Aim Status", "Running");
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//tell the command when the PID controller is on target

    	if(Double.isNaN(angle)){
    		SmartDashboard.putString("Aim Status", "Bad Angle");
    		return true;
    	}
    	if(gyroPIDController.onTarget()){
    		SmartDashboard.putString("Aim Status", "On Target");
    		return true; 
    	} else {
    		SmartDashboard.putString("Aim Status", "Not On Target");
    		return false;  
    	}
    			//Robot.drivetrain.gyroPIDController.onTarget();
    }
    
    

    // Called once after isFinished returns true
    protected void end() {
    	

    	gyroPIDController.disable();
        gyroPIDController.free();

        SmartDashboard.putString("Aim Status", "End");
      
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
