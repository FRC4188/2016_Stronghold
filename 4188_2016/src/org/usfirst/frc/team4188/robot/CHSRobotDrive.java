package org.usfirst.frc.team4188.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * @author Erin King
 */
public class CHSRobotDrive extends RobotDrive implements PIDOutput {
    static final int kFrontLeft_val = 0;
    static final int kFrontRight_val = 1;
    static final int kRearLeft_val = 2;
    static final int kRearRight_val = 3;
    static final double minValue = 0.17;
    
    public CHSRobotDrive(final int leftMotorChannel, final int rightMotorChannel) {
        super(leftMotorChannel, rightMotorChannel);
    }

    public CHSRobotDrive(final int frontLeftMotor, final int rearLeftMotor,
            final int frontRightMotor, final int rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    public CHSRobotDrive(SpeedController leftMotor, SpeedController rightMotor) {
        super(leftMotor, rightMotor);
    }

    public CHSRobotDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor,
            SpeedController frontRightMotor, SpeedController rearRightMotor) {
        super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    public void pidWrite(double output) {
        System.out.println(output);
        if(Math.abs(output)<minValue){
            if(output<0)
            {
                output= -minValue;
            }
            else
            {
                output= minValue;
            }
            System.out.println(" >"+output);
        }
        
    }
}