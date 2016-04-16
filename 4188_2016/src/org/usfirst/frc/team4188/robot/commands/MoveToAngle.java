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
public class MoveToAngle extends Command {

	public PIDController driveTrainPIDController = RobotMap.driveTrainPIDController;
	
	 
	//private static final double KP = 0.1;
	//private static final double KI = 0.005;
	//private static final double KD = 0.0;
	//private static final double KF = 6.0;
	private static final double KP = 0.03;
	private static final double KI = 0.00001;
	private static final double KD = 0.0;
	
/**
	private static final double KP = 0.025;
	private static final double KI = 0.0002;
	private static final double KD = 0.0;
	*/
	/**
	 double KP = SmartDashboard.getNumber("Kp value");
	 double KI = SmartDashboard.getNumber("Ki value");
	 double KD = SmartDashboard.getNumber("Kd value");
	 double KF = SmartDashboard.getNumber("Kf value");
	**/
	 
	private double angle ;
	
	
    public MoveToAngle() {
        // Use requires() here to declare subsystem dependencies 
    	requires(Robot.drivetrain);
    	//this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {  
    	SmartDashboard.putString("Aim Status", "Initializing");

/*
gyroPIDController = new PIDController(KP, KI, KD, KF, RobotMap.driveTrainGyro, RobotMap.driveBase);
*/
    	
driveTrainPIDController = new PIDController(KP, KI, KD, RobotMap.driveTrainGyro, RobotMap.driveBase);
    	new CameraLightsOff();
    	//angle = Robot.getAimError();
    	angle = 180;
    	
    	if(!Double.isNaN(angle)){
    		Robot.drivetrain.gyroReset();
	    	//Robot.drivetrain.goToAngle(90);
    		//RobotMap.driveTrainGyro.reset();
    		driveTrainPIDController.setAbsoluteTolerance(1);
    		driveTrainPIDController.setSetpoint(angle);
            //if(!gyroPIDController.isEnabled()); gyroPIDController.enable();
    		driveTrainPIDController.enable();
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
    	SmartDashboard.putString("Move Status", "Running");
   }

    // Make this return true when this Command no longer needs to run execute()
    private boolean prevOnTarget = false;
    protected boolean isFinished() {
    	boolean result = false;
    	//tell the command when the PID controller is on target
    	if(Double.isNaN(angle)){
    		SmartDashboard.putString("Move Status", "Bad Angle");
    		result = true;
    	}
    	if(driveTrainPIDController.onTarget() && prevOnTarget){
    		SmartDashboard.putString("Move Status", "On Target");
    		result = true; 
    	} else {
    		SmartDashboard.putString("Move Status", "Not On Target");
    		result = false;  
    	}
    			//Robot.drivetrain.gyroPIDController.onTarget();
    	prevOnTarget = driveTrainPIDController.onTarget();
    	return result;
    }
    
    

    // Called once after isFinished returns true
    protected void end() {
    	

    	driveTrainPIDController.disable();
        driveTrainPIDController.free();

        SmartDashboard.putString("Move Status", "End");
      
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
