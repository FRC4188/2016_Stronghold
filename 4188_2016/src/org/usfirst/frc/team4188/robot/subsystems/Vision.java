package org.usfirst.frc.team4188.robot.subsystems;

import java.io.IOException;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	protected int session;
    protected Image frame;
    protected AxisCamera camera;
    protected String ip;
    protected String cam;
    
    public Vision(String cam)
    {
    	//this.ip = ip;
    	this.cam = cam;
    }

    public void initialize()
	{
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        camera = new AxisCamera(ip);   
	}
    
    public void initUSBCam()
    {
    	CameraServer camera = CameraServer.getInstance();
        camera.setQuality(50);
        camera.startAutomaticCapture(cam);
    }
	
	public void stream()
	{	        
		camera.getImage(frame);
        CameraServer.getInstance().setImage(frame);
	}
	
	/**
	 * Runs GRIP image processing on the RoboRIO. Required for GRIP vision usage.
	 * @param filename name of GRIP file on RoboRIO.
	 */
	
	public static void runGRIP(String filename)
	{
		try
		{
			Runtime.getRuntime().exec(new String[]{"/usr/local/frc/JRE/bin/java", "-jar", "grip.jar", filename});
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void init() {
		// TODO Auto-generated method stub
		
	}
    
}

