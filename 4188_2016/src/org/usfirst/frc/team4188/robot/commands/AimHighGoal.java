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
	
//	PID tuned for competition bot
//	private static final double KP = 0.03;
//	private static final double KI = 0.00001;
//	private static final double KD = 0.0;
	
	
	//PID tuned for practice bot
	private static final double KP = 0.03;
	private static final double KI = 0.00002;
	private static final double KD = 0.0;
	 
	private double angle;
	
	
	
    public AimHighGoal() {
        // Use requires() here to declare subsystem dependencies 
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {  
    	SmartDashboard.putString("Aim Status", "Initializing");

        /*
        gyroPIDController = new PIDController(KP, KI, KD, KF, RobotMap.driveTrainGyro, RobotMap.driveBase);
        */
    	
        gyroPIDController = new PIDController(KP, KI, KD, RobotMap.driveTrainGyro, RobotMap.driveBase);
    	new CameraLightsOff();
    	//angle = Robot.getAimError();
    	
    	angle = 45;
    	
    	if(!Double.isNaN(angle)){
    		Robot.drivetrain.gyroReset();
    		gyroPIDController.setAbsoluteTolerance(1.0);
    		gyroPIDController.setSetpoint(angle);
            //if(!gyroPIDController.isEnabled()); gyroPIDController.enable();
    		gyroPIDController.enable();
    	}	

    }
    
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Aim Status", "Running");
   }

    // Make this return true when this Command no longer needs to run execute()
    private boolean prevOnTarget = false;
    
    protected boolean isFinished() {
    	boolean result = false;
    	//tells the command when the PID controller is on target
    	if(Double.isNaN(angle)){
    		SmartDashboard.putString("Aim Status", "Bad Angle");
    		result = true;
    	}
    	if(gyroPIDController.onTarget() && prevOnTarget){
    		SmartDashboard.putString("Aim Status", "On Target");
    		result = true; 
    	} else {
    		SmartDashboard.putString("Aim Status", "Not On Target");
    		result = false;  
    	}
    	prevOnTarget = gyroPIDController.onTarget();
    	return result;
    }
    
    

    // Called once after isFinished returns true
    protected void end() {
    	
    	gyroPIDController.disable();
        gyroPIDController.free();
      
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

