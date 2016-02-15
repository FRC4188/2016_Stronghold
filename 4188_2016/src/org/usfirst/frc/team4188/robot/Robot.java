
package org.usfirst.frc.team4188.robot;

import java.io.IOException;

import org.usfirst.frc.team4188.robot.subsystems.CameraLights;
import org.usfirst.frc.team4188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4188.robot.subsystems.Retriever;
import org.usfirst.frc.team4188.robot.subsystems.Scaler;
import org.usfirst.frc.team4188.robot.subsystems.Shooter;
import org.usfirst.frc.team4188.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	private static final String LIFECAM_USB_CAM = "cam1";
	public static DriveTrain drivetrain;
	public static OI oi;
	public static Retriever robotRetriever;
	public static CameraLights cameraLights;
	public static Shooter robotShooter;
	public static Scaler robotScaler;
	public static Vision robotVision;
    Command autonomousCommand;
    SendableChooser chooser;

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
        chooser = new SendableChooser();
        robotVision = new Vision(LIFECAM_USB_CAM);
        oi = new OI();
        //chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        
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
        
        
        Robot.robotShooter.runShooterMotors(oi.copilotJoystick.getThrottle());
        
        
        
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
        //autonomousCommand = (Command) chooser.getSelected();
      
        Robot.drivetrain.resetEncoders();
        
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.drivetrain.resetEncoders();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.drivetrain.getEncoderValues();
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(robotRetriever);
        Robot.robotShooter.runShooterMotors(oi.copilotJoystick.getZ());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
