package org.usfirst.frc.team4188.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;


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
	

	


public OI(){
	pilotJoystick = new CHSJoystick(0,4,12,-12.0,12.0,1,1.0,-12.0,12.0,1,1.0,-12.0,12.0,1,1.0);
	
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
	
	
	
	}




public Joystick getpilotJoystick(){
	return pilotJoystick;
	}




}



