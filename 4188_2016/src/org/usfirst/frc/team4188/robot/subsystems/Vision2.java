package org.usfirst.frc.team4188.robot.subsystems;

//import java.io.IOException;

//import com.ni.vision.NIVision;
import java.util.Comparator;
import java.util.Vector;

import org.usfirst.frc.team4188.robot.CHSLog;
import org.usfirst.frc.team4188.robot.RobotMap;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.VisionException;













import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
//import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class Vision2 extends Subsystem {
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
		double PercentAreaToImageArea;
		double Area;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		
		public int compareTo(ParticleReport r)
		{
			return (int)(r.Area - this.Area);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int)(r1.Area - r2.Area);
		}
	};
	
	public class Scores{
		double Area;
		double Aspect;
	}
	
	Image frame;
	Image binaryFrame;
	int imaqError;
	AxisCamera camera;
	
	NIVision.Range GOAL_HUE_RANGE = new NIVision.Range(45, 87);	//Default hue range for yellow tote
	NIVision.Range GOAL_SAT_RANGE = new NIVision.Range(53, 255);	//Default saturation range for yellow tote
	NIVision.Range GOAL_VAL_RANGE = new NIVision.Range(92, 255);	//Default value range for yellow tote
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 2.22; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 1.4; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 75.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 49.4; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	Scores scores = new Scores();
	

	public Vision2(String ip){
		this.camera = new AxisCamera(ip); 
    	this.camera.writeResolution(AxisCamera.Resolution.k320x240);
    	this.camera.writeMaxFPS(10);
    	this.camera.writeCompression(30);
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);

		//Put default values to SmartDashboard so fields will appear
		SmartDashboard.putNumber("Tote hue min", GOAL_HUE_RANGE.minValue);
		SmartDashboard.putNumber("Tote hue max", GOAL_HUE_RANGE.maxValue);
		SmartDashboard.putNumber("Tote sat min", GOAL_SAT_RANGE.minValue);
		SmartDashboard.putNumber("Tote sat max", GOAL_SAT_RANGE.maxValue);
		SmartDashboard.putNumber("Tote val min", GOAL_VAL_RANGE.minValue);
		SmartDashboard.putNumber("Tote val max", GOAL_VAL_RANGE.maxValue);
		SmartDashboard.putNumber("Area min %", AREA_MINIMUM);
	}
	
	public void process(){
		camera.getImage(frame);
		CameraServer.getInstance().setImage(frame);
		
		
		
		GOAL_HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Tote hue min", GOAL_HUE_RANGE.minValue);
		GOAL_HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote hue max", GOAL_HUE_RANGE.maxValue);
		GOAL_SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Tote sat min", GOAL_SAT_RANGE.minValue);
		GOAL_SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote sat max", GOAL_SAT_RANGE.maxValue);
		GOAL_VAL_RANGE.minValue = (int)SmartDashboard.getNumber("Tote val min", GOAL_VAL_RANGE.minValue);
		GOAL_VAL_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote val max", GOAL_VAL_RANGE.maxValue);
	
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, GOAL_HUE_RANGE, GOAL_SAT_RANGE, GOAL_VAL_RANGE);
	 int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
	 SmartDashboard.putNumber("Masked particles", numParticles);
	
	 float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM);
	 criteria[0].lower = areaMin;
	 
	 imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
	 
	 numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
	
	 if(numParticles > 0)
	 {
		 Vector<ParticleReport> particles = new Vector<ParticleReport>();
		 for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
		 {
			ParticleReport par = new ParticleReport();
			par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
			par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
			par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
			par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
			par.BoundingRectBottom = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
			par.BoundingRectRight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
			particles.add(par);


			 
		 }
		 
		 
		 scores.Aspect = AspectScore(particles.elementAt(0));
			SmartDashboard.putNumber("Aspect", scores.Aspect);
			scores.Area = AreaScore(particles.elementAt(0));
			SmartDashboard.putNumber("Area", scores.Area);
			boolean isGoalHot = scores.Aspect > SCORE_MIN && scores.Area > SCORE_MIN;

			//Send distance and tote status to dashboard. The bounding rect, particularly the horizontal center (left - right) may be useful for rotating/driving towards a tote
			SmartDashboard.putBoolean("IsGoalHot", isGoalHot);
			SmartDashboard.putNumber("Distance", computeDistance(binaryFrame, particles.elementAt(0)));
		} else {
			SmartDashboard.putBoolean("IsGoalHot", false);
		}

		Timer.delay(0.005);	
		 
		 
		 
		 
	 }
	 
	 
	 
	double ratioToScore(double ratio)
	{
		return (Math.max(0, Math.min(100*(1-Math.abs(1-ratio)), 100)));
	}

	double AreaScore(ParticleReport report)
	{
		double boundingArea = (report.BoundingRectBottom - report.BoundingRectTop) * (report.BoundingRectRight - report.BoundingRectLeft);
		//Tape is 7" edge so 49" bounding rect. With 2" wide tape it covers 24" of the rect.
		return ratioToScore((49/24)*report.Area/boundingArea);
	}

	/**
	 * Method to score if the aspect ratio of the particle appears to match the retro-reflective target. Target is 7"x7" so aspect should be 1
	 */
	double AspectScore(ParticleReport report)
	{
		return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop)));
	}

	/**
	 * Computes the estimated distance to a target using the width of the particle in the image. For more information and graphics
	 * showing the math behind this approach see the Vision Processing section of the ScreenStepsLive documentation.
	 *
	 * @param image The image to use for measuring the particle estimated rectangle
	 * @param report The Particle Analysis Report for the particle
	 * @param isLong Boolean indicating if the target is believed to be the long side of a tote
	 * @return The estimated distance to the target in feet.
	 */
	double computeDistance (Image image, ParticleReport report) {
		double normalizedWidth, targetWidth;
		NIVision.GetImageSizeResult size;

		size = NIVision.imaqGetImageSize(image);
		normalizedWidth = 2*(report.BoundingRectRight - report.BoundingRectLeft)/size.width;
		targetWidth = 7;

		return  targetWidth/(normalizedWidth*12*Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
	}

	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}













