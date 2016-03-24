
package org.usfirst.frc.team4188.robot;

import java.io.IOException;

import org.usfirst.frc.team4188.robot.commands.AimHighGoal;
import org.usfirst.frc.team4188.robot.commands.DriveForwardAutonomous;
import org.usfirst.frc.team4188.robot.commands.DriveForwardAutonomousMoat;
import org.usfirst.frc.team4188.robot.commands.DriveForwardRetrieverUpAutonomous;
import org.usfirst.frc.team4188.robot.commands.DriveForwardTurnRightAutonomous;
import org.usfirst.frc.team4188.robot.commands.LowBarAutonomous;
import org.usfirst.frc.team4188.robot.commands.RockWallAuto;
import org.usfirst.frc.team4188.robot.commands.SensorsDisplay;
import org.usfirst.frc.team4188.robot.subsystems.CameraLights;
import org.usfirst.frc.team4188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4188.robot.subsystems.Retriever;
import org.usfirst.frc.team4188.robot.subsystems.Scaler;
import org.usfirst.frc.team4188.robot.subsystems.Shooter;
import org.usfirst.frc.team4188.robot.subsystems.Vision;

import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//private static final String LIFECAM_USB_CAM = "cam0";//CHANGE TO CAM 0 ON OFFICIAL ROBOT
	 private static final String AXIS_CAMERA_IP = "axis-camera";
	public static DriveTrain drivetrain;
	public static OI oi;
	public static Retriever robotRetriever;
	public static CameraLights cameraLights;
	public static Shooter robotShooter;
	public static Scaler robotScaler;
	public static Vision robotVision;
	public static SensorsDisplay sensors;
	private final NetworkTable grip = NetworkTable.getTable("grip");

	
    
	Command autonomousCommand;
    SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    	NetworkTable table;
    
       
    
    	
		public void robotInit() {
		RobotMap.init();
		
	   
		
		drivetrain = new DriveTrain();
		robotRetriever = new Retriever();
		cameraLights = new CameraLights();
		robotShooter = new Shooter();
		robotScaler = new Scaler();
        robotVision = new Vision(AXIS_CAMERA_IP);
        oi = new OI();
     
        //chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());

        autoChooser = new SendableChooser();
        autoChooser.addDefault("Regular Low Bar Autonomous :)", new DriveForwardAutonomous());
        autoChooser.addDefault("Low Goal Shot Autonomous :(", new DriveForwardTurnRightAutonomous());
        autoChooser.addDefault("Moat Autonomous :)", new DriveForwardAutonomousMoat());
        autoChooser.addDefault("Rugged Terrain Autonomous :)", new DriveForwardRetrieverUpAutonomous());
        autoChooser.addDefault("Drive Forward Low Bar Repeat Autonomous :(", new LowBarAutonomous());
        autoChooser.addDefault("Rock Wall Autonomous :)", new RockWallAuto());
        SmartDashboard.putData("AUTONOMOUS CHOOSER", autoChooser);
        
        sensors = new SensorsDisplay(); 
        /*
        chooser.addDefault("Move Forward Autonomous", new AutoDrive(36,0));
        chooser.addDefault("Move Forward Autonomous", new AutoDrive(36,30));
        */
        
        drivetrain.init();
        robotRetriever.init();
        robotShooter.init();
        cameraLights.init();
        robotScaler.init();
        robotVision.init();
        
        
      //  Robot.robotShooter.runShooterMotors(Robot.oi.copilotJoystick.getThrottle());
        
        
    	}
        
          
     /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) autoChooser.getSelected();
      
       // Robot.drivetrain.resetEncoders();
        
        
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        /*
        for (double area : grip.getNumberArray("targets/area", new double[0])) {
            System.out.println("Got contour with area=" + area);
        } */
    } 

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.drivetrain.setRampRate(0.12);
       // Robot.
        drivetrain.resetEncoders();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       
    	
    	Scheduler.getInstance().run();
    	
    	/**
    	ParticleAnalysisReport[] reports = null;
		try {
			
			
			reports = robotVision.getReports(robotVision.getProcessedImage());
		
		
		} catch (NIVisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	**/
       // Robot.drivetrain.getEncoderValues();
       SmartDashboard.putData("PID Controller", drivetrain.gyroPIDController);
       //SmartDashboard.putData(robotRetriever);
        SmartDashboard.putNumber("Throttle Value", Robot.oi.copilotJoystick.getThrottle());
        SmartDashboard.putNumber("FPGA Timer Value", Timer.getFPGATimestamp());
        SmartDashboard.putNumber("Gyro Center Value", RobotMap.driveTrainGyro.getAngle());
        SmartDashboard.putData("Aim High Goal Test", new AimHighGoal());
        //SmartDashboard.putData( "DriveBase", drivetrain.robotDrive);
        //SmartDashboard.putNumber("Number of Particles", reports.length);
        
        /**
        for(int i = 0; i < reports.length; i ++){
        	ParticleAnalysisReport r = reports[i];
        	 SmartDashboard.putNumber("Particle "+i +"Height: " , r.boundingRectHeight );
        	 SmartDashboard.putNumber("Particle " + i + "Width", r.boundingRectWidth);
        	 SmartDashboard.putNumber("Particle "+ i + "Center X" , r.center_mass_x);
        	 SmartDashboard.putNumber("Particle " + i + "Center Y", r.center_mass_y);
        	 
        }
        **/  
       // Robot.robotShooter.runShooterMotors(oi.copilotJoystick.getZ());
        
       try {
		robotVision.getRawImage();
		} catch (NIVisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       catch (VisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       //SmartDashboard.putData(robotVision);
    }

        
    
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        //LiveWindow.addActuator("DriveTrain", "gyro", drivetrain.gyroPIDController);
    	LiveWindow.run();
    }
}


