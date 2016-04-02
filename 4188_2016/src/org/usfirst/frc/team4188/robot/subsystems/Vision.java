package org.usfirst.frc.team4188.robot.subsystems;

//import java.io.IOException;

//import com.ni.vision.NIVision;
import org.usfirst.frc.team4188.robot.CHSLog;
import org.usfirst.frc.team4188.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.VisionException;







//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
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
    private ParticleFilterCriteria2 criteria [];
    private HSLImage original;
    private BinaryImage thresholdImage, targetImage, filledInImage, filteredImage;
    //protected String ip;
    //protected String cam;
    /**
    public Vision(String cam)
    {
    	//this.ip = ip;
    	this.cam = cam;
    
    }
    **/
    
    
    public Vision(String ip)
    {
    	//this.ip = ip;
    	this.camera = new AxisCamera(ip); 
    	camera.writeResolution(AxisCamera.Resolution.k320x240);
    	camera.writeMaxFPS(10);
    	camera.writeCompression(30);
    }

    public void init()
    {
//        this.camera = new AxisCamera(ip); 
    	  criteria = new NIVision.ParticleFilterCriteria2[2];
    	 criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH, 25, 400, 0, 0);
    	 criteria[1] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_BOUNDING_RECT_HEIGHT, 20, 400, 0,0);
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
	/**
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
	**/
    
   
    
    

	public ParticleAnalysisReport[] getProcessedImage()  {
		final int 
		H_LOW = 45,
		H_HIGH = 87,
		S_LOW = 53,
		S_HIGH = 255,
		V_LOW = 92,
		V_HIGH = 255;
		
		try {
			thresholdImage = getHSLImage().thresholdHSV(H_LOW, H_HIGH, S_LOW, S_HIGH, V_LOW, V_HIGH);
			
			targetImage = thresholdImage;	
			filledInImage = thresholdImage.particleFilter(criteria);
			filteredImage =	filledInImage.convexHull(false);
				
			//filteredImage = filledInImage.particleFilter(criteria); // DO THIS IF REMOVE SMALL OBJECTS DOES NOT WORK
				//filteredImage =	filledInImage.removeSmallObjects(false, 2);
			
			if (filteredImage.getNumberParticles() != 0)
	             return filteredImage.getOrderedParticleAnalysisReports();
	         else return null;
		} catch (NIVisionException e) {
			e.printStackTrace();
			return null;
		} catch (VisionException ex){
			ex.printStackTrace();
			return null;
		}
		finally{
			freeImages();
		}
		 
	}
	
	public HSLImage getHSLImage() throws NIVisionException {
		
		original = camera.getImage();
		return original;
	}
	

	/**
	public double calculatePanTargetAngle(ParticleAnalysisReport target, double distanceToTopTarget, ParticleAnalysisReport otherTarget) {
        
		double center_center = target.center_mass_x;
        //double side_center = otherTarget.center_mass_x;
        double image_width = target.imageWidth;
        double angle = FOV_RADS*((center_center-(image_width/2))/image_width);
        //double phi = FOV_RADS*((side_center-center_center)/image_width);
        //CorpsLog.log("phi",phi,false,true);
        //Camera on test bot is angled slightly off shooter's center...
        angle = Math.toDegrees(angle);
        CHSLog.log("original pan angle",angle,false,true);
        //angle = adjustAngle(angle,phi,distanceToTopTarget,otherTarget);
        angle = angle + RobotMap.getPanTrim();
        CHSLog.log("Target pan position",angle,true,true);
        return angle;
    }
	
	**/
/**	public ParticleAnalysisReport [] getReports(BinaryImage target) throws NIVisionException{
		return target.getOrderedParticleAnalysisReports();
	}
**/

	private void freeImages() {
        try {
            if(original!=null) original.free();
            if(thresholdImage!=null) thresholdImage.free();
            if(targetImage!=null) targetImage.free();
            if(filledInImage!=null) filledInImage.free();
            if(filteredImage!=null) filteredImage.free();
        }
        catch (NIVisionException ex) {
        }
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

