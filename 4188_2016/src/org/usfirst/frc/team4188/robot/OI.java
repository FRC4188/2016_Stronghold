package org.usfirst.frc.team4188.robot;



import org.usfirst.frc.team4188.robot.commands.AimHighGoal;
import org.usfirst.frc.team4188.robot.commands.AimHighGoalSequence;
import org.usfirst.frc.team4188.robot.commands.AutoShoot;
import org.usfirst.frc.team4188.robot.commands.AutoShoot2;
import org.usfirst.frc.team4188.robot.commands.AutoShoot3;
import org.usfirst.frc.team4188.robot.commands.CameraLightsOff;
import org.usfirst.frc.team4188.robot.commands.CameraLightsOn;
import org.usfirst.frc.team4188.robot.commands.DoNothingRetrieverSolenoid;
import org.usfirst.frc.team4188.robot.commands.EjectBallFullSpeed;
import org.usfirst.frc.team4188.robot.commands.EjectBallLowGoal;
import org.usfirst.frc.team4188.robot.commands.EndAimHighGoal;
import org.usfirst.frc.team4188.robot.commands.GearShiftDoNothing;
import org.usfirst.frc.team4188.robot.commands.LowSpeedTwist;
import org.usfirst.frc.team4188.robot.commands.ResetSensors;
import org.usfirst.frc.team4188.robot.commands.RetrieveBall;
import org.usfirst.frc.team4188.robot.commands.RetrieveBallFullSpeed;
import org.usfirst.frc.team4188.robot.commands.RetrieverIn;
import org.usfirst.frc.team4188.robot.commands.RetrieverOut;
import org.usfirst.frc.team4188.robot.commands.RunShooterMotorBackwards;
import org.usfirst.frc.team4188.robot.commands.RunShooterMotors;
import org.usfirst.frc.team4188.robot.commands.RunShooterMotorsBackwardWithThrottle;
import org.usfirst.frc.team4188.robot.commands.RunShooterMotorsWithThrottle;
import org.usfirst.frc.team4188.robot.commands.ScalerDown;
import org.usfirst.frc.team4188.robot.commands.ScalerUp;
import org.usfirst.frc.team4188.robot.commands.SeizeDriveTrain;
import org.usfirst.frc.team4188.robot.commands.ShiftDriveGearBackward;
import org.usfirst.frc.team4188.robot.commands.ShiftDriveGearForward;
import org.usfirst.frc.team4188.robot.commands.ShooterBackward;
import org.usfirst.frc.team4188.robot.commands.ShooterDoNothing;
import org.usfirst.frc.team4188.robot.commands.ShooterForward;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.*;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

	public CHSJoystick pilotJoystick;
	public CHSJoystick copilotJoystick;
	
	public JoystickButton pilot1;
	public JoystickButton pilot2;
	public JoystickButton pilot3;
	public JoystickButton pilot4;
	public JoystickButton pilot5;
	public JoystickButton pilot6;
	public JoystickButton pilot7;
	public JoystickButton pilot8;
	public JoystickButton pilot9;
	public JoystickButton pilot10;
	public JoystickButton pilot11;
	public JoystickButton pilot12;
	
 	public JoystickButton copilot1;
    public JoystickButton copilot2;
    public JoystickButton copilot3;
    public JoystickButton copilot4;
    public JoystickButton copilot5;
    public JoystickButton copilot6;
    public JoystickButton copilot7;
    public JoystickButton copilot8;
    public JoystickButton copilot9;
    public JoystickButton copilot10;
    public JoystickButton copilot11;

    CameraServer camServer;

    private static final String LIFECAM_USB_CAM = "cam0";//CHANGE ON TO CAM 1 ON PRACTICE BOT AND CAM 0 ON OFFICIAL BOT
    private static final boolean SHOW_LIFECAM = false;
    
    private static final int PILOT_PORT = 0;
    private static final int PILOT_NUM_AXES = 4;
    private static final int PILOT_NUM_BUTTONS = 12;
    
    private static final int COPILOT_PORT = 1;
    private static final int COPILOT_NUM_AXES = 3;
    private static final int COPILOT_NUM_BUTTONS = 11;
    
    //Keep track of an instantiated AimHighGoal object, so we can cancel it if needed.
    private static AimHighGoalSequence aimHighGoalSequence; 
	    
    public OI(){
	
    	aimHighGoalSequence = new AimHighGoalSequence();
    	
		pilotJoystick = new CHSJoystick(PILOT_PORT, PILOT_NUM_AXES, PILOT_NUM_BUTTONS);
		pilotJoystick.xDeadZone(-12.0,12.0).xMult(1).xMaxSpeed(1.0);
		pilotJoystick.yDeadZone(-12.0,12.0).yMult(1).yMaxSpeed(1.0);
		pilotJoystick.twistDeadZone(-12.0,12.0).twistMult(1).twistMaxSpeed(1.0);
		
		copilotJoystick = new CHSJoystick(COPILOT_PORT, COPILOT_NUM_AXES, COPILOT_NUM_BUTTONS);
		copilotJoystick.xDeadZone(-5,5).xMult(1).xMaxSpeed(1);
		copilotJoystick.yDeadZone(-12.0,12.0).yMult(1).yMaxSpeed(1.0);
		copilotJoystick.twistDeadZone(-12.0,12.0).twistMult(1).twistMaxSpeed(1.0);
		
		
		pilot1 = new JoystickButton(pilotJoystick, 1);
	    pilot2 = new JoystickButton(pilotJoystick, 2);
	    pilot3 = new JoystickButton(pilotJoystick, 3);
	    pilot4 = new JoystickButton(pilotJoystick, 4);
	    pilot5 = new JoystickButton(pilotJoystick, 5);
	    pilot6 = new JoystickButton(pilotJoystick, 6);
	    pilot7 = new JoystickButton(pilotJoystick, 7);
	    pilot8 = new JoystickButton(pilotJoystick, 8);
	    pilot9 = new JoystickButton(pilotJoystick, 9);
	    pilot10 = new JoystickButton(pilotJoystick, 10);
	    pilot11 = new JoystickButton(pilotJoystick, 11);
	    pilot12 = new JoystickButton(pilotJoystick, 12);
	    
	    copilot1 = new JoystickButton(copilotJoystick, 1);
	    copilot2 = new JoystickButton(copilotJoystick, 2);
	    copilot3 = new JoystickButton(copilotJoystick, 3);
	    copilot4 = new JoystickButton(copilotJoystick, 4);
	    copilot5 = new JoystickButton(copilotJoystick, 5);
	    copilot6 = new JoystickButton(copilotJoystick, 6);
	    copilot7 = new JoystickButton(copilotJoystick, 7);
	    copilot8 = new JoystickButton(copilotJoystick, 8);
	    copilot9 = new JoystickButton(copilotJoystick, 9);
	    copilot10 = new JoystickButton(copilotJoystick, 10);
	    copilot11 = new JoystickButton(copilotJoystick, 11);
	    
	    
	    
	    pilot11.whileHeld(new ShiftDriveGearForward());
	    pilot11.whenReleased(new GearShiftDoNothing());
	    pilot12.whileHeld(new ShiftDriveGearBackward());
	    pilot12.whenReleased(new GearShiftDoNothing());
	    
	    
	    copilot5.whileHeld(new EjectBallLowGoal());
	    copilot4.whileHeld(new RetrieveBall());

	    
	    copilot6.whileHeld(new RetrieveBallFullSpeed());
	    copilot7.whileHeld(new EjectBallFullSpeed());
	    
	    copilot2.whileHeld(new RetrieverOut());
	    copilot2.whenReleased(new DoNothingRetrieverSolenoid());
		copilot3.whileHeld(new RetrieverIn());
		copilot3.whenReleased(new DoNothingRetrieverSolenoid());
		
		pilot8.whileHeld(new RetrieverOut());
		pilot8.whenReleased(new DoNothingRetrieverSolenoid());
		pilot7.whileHeld(new RetrieverIn());
		pilot7.whenReleased(new DoNothingRetrieverSolenoid());

		pilot10.whileHeld(new EjectBallFullSpeed());
		pilot9.whileHeld(new RetrieveBall());
		
		pilot3.whileHeld(new ScalerUp());
		pilot5.whileHeld(new ScalerDown());
		
		pilot6.cancelWhenPressed(aimHighGoalSequence);
		
		copilot9.whileHeld(new RunShooterMotorsWithThrottle());
		copilot8.whileHeld(new RunShooterMotorsBackwardWithThrottle());

		copilot10.whenPressed(new CameraLightsOn());
		copilot11.whenPressed(new CameraLightsOff());
		
		
		
		copilot1.whenPressed(aimHighGoalSequence);
		pilot1.whenPressed(new AutoShoot3());
		//copilot1.whenPressed(new AutoShoot3());
		//Not sure if cancelWhenPressed will be happy when aimHighGoal is null :(
		
	
		pilot4.whenPressed(aimHighGoalSequence);
		
		if	(SHOW_LIFECAM) {
			camServer = CameraServer.getInstance();
	        camServer.setQuality(50);
	        //the camera name (ex "cam0") can be found through the roborio web interface
	        camServer.startAutomaticCapture(LIFECAM_USB_CAM);
		}
	}

	public Joystick getpilotJoystick(){
		return pilotJoystick;
	}
	
	public Joystick getcopilotJoystick(){
		return copilotJoystick;
}



}



