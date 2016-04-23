package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.CHSRobotDrive.PIDType;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
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
	private static final double KP = 0.011;
	private static final double KI = 0.0;
	private static final double KD = 0.0;
	 
	private double angle;
	private double tolerance;
	
    public AimHighGoal(double tolerance) {
        // Use requires() here to declare subsystem dependencies 
    	requires(Robot.drivetrain);
    	this.tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {  
    	SmartDashboard.putString("Aim Status", "Initializing");

        /*
        gyroPIDController = new PIDController(KP, KI, KD, KF, RobotMap.driveTrainGyro, RobotMap.driveBase);
        */
    	
    	RobotMap.driveBase.setPIDType(PIDType.turnToAngle);
    	gyroPIDController = new PIDController(KP, KI, KD, RobotMap.driveTrainGyro, RobotMap.driveBase);
    	new CameraLightsOff();
    	angle = Robot.getAimError();
    	
    	if(!Double.isNaN(angle)){
    		Robot.drivetrain.gyroReset();
    		gyroPIDController.setAbsoluteTolerance(tolerance);
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
    protected boolean isFinished() {
    	if(gyroPIDController.onTarget()){
    		SmartDashboard.putString("Aim Status", "On Target");
    	} else {
    		SmartDashboard.putString("Aim Status", "Not On Target");
    	}
    	return gyroPIDController.onTarget();
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

