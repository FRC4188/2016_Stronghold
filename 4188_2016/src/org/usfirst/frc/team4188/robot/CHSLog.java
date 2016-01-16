package org.usfirst.frc.team4188.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CHSLog {
    private static final boolean SMARTDASHBOARD = true, ENABLE_CONSOLE = true;
    public static void log(String title, String message, boolean dashboard, boolean console) {
        if(console && ENABLE_CONSOLE) System.out.println(title+": "+message);
        if(dashboard && SMARTDASHBOARD) SmartDashboard.putString(title, message);
    }
    public static void log(String title, double value, boolean dashboard, boolean console) {
        if(console && ENABLE_CONSOLE) System.out.println(title+": "+value);
        if(dashboard && SMARTDASHBOARD) SmartDashboard.putNumber(title, value);
    }
    public static void log(String title, int value, boolean dashboard, boolean console) {
        if(console && ENABLE_CONSOLE) System.out.println(title+": "+value);
        if(dashboard && SMARTDASHBOARD) SmartDashboard.putNumber(title, value);
    }
    public static void log(String title, boolean value, boolean dashboard, boolean console) {
        if(console && ENABLE_CONSOLE) System.out.println(title+": "+value);
        if(dashboard && SMARTDASHBOARD) SmartDashboard.putBoolean(title, value);
    }
}