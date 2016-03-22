package org.usfirst.frc.team4188.robot.subsystems;

//import java.io.IOException;

//import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
//import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
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
    //protected String ip;
    //protected String cam;
    /**
    public Vision(String cam)
    {
    	//this.ip = ip;
    	this.cam = cam;
    
    }
    **/
    public Vision(String ip){
    	//this.ip = ip;
    	this.camera = new AxisCamera(ip); 
    }

    public void init()
    {
//        this.camera = new AxisCamera(ip); 
        
	}
    

	
	
	/**
	 * Runs GRIP image processing on the RoboRIO. Required for GRIP vision usage.
	 * @param filename name of GRIP file on RoboRIO.
	 */
	
	/**
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
	 * @throws NIVisionException 
	
	
	
**/
	
	public HSLImage getRawImage() throws NIVisionException, VisionException{
		//HSLImage derp = new HSLImage();
		//camera.getImage(derp);
		HSLImage cameraImage = camera.getImage();
		return cameraImage;
		//return derp;
	}
	
	public BinaryImage colorFilterImage(HSLImage rawImage) throws NIVisionException{
		final int 
				H_LOW = 45,
				H_HIGH = 87,
				S_LOW = 53,
				S_HIGH = 255,
				V_LOW = 92,
				V_HIGH = 255;
			return rawImage.thresholdHSV(H_LOW, H_HIGH, S_LOW, S_HIGH, V_LOW, V_HIGH);
			
		
		
	}
	
	public BinaryImage shapeFilterImage(BinaryImage beforeImage) throws NIVisionException{
	
	BinaryImage targetImage = null;
	
	try{
		 targetImage = beforeImage.convexHull(false).removeSmallObjects(true, 2);
	}
	catch (VisionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		return targetImage;
		
	}
	
	public BinaryImage getProcessedImage() throws NIVisionException{
		HSLImage rawImage = camera.getImage();
		System.out.println(rawImage);
		BinaryImage filteredImageColor = colorFilterImage(rawImage);
		BinaryImage filteredImageShape = shapeFilterImage(filteredImageColor);
		return filteredImageShape;
	}
	
	public ParticleAnalysisReport [] getReports(BinaryImage target) throws NIVisionException{
			System.out.println(target);
		return target.getOrderedParticleAnalysisReports();
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

