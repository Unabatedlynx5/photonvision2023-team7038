/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Map;
import java.util.HashMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An entire class for debugging?? YES! This makes debugging really easy :)
 * Note - roborio logs will be invisible during competition gameplay.
 * Example:
 * 
 * import frc.robot.Subsystems.Debug;
 * Debug debug = new Debug('this is what I want these debug messages to be labeled as!');
 */
public class Debug {
    private final String m_messenger;
    private boolean m_enabled = false;

    public Debug(String messenger) {
        m_messenger = messenger;
        System.out.println("debugging logger created for: " + m_messenger + ", enabled: " + m_enabled);
    }

    /**
     * Prints a message to the console. Formats it with the message sender in front.
     * 
     * @param message the message to print
     */
    public void log(String message) {
        String formattedMessage = m_messenger + " - " + message + " \n";
        
        if (m_enabled) {
            System.out.println(formattedMessage);
        }
    }

    /**
     * Prints a message to the console only when it changes
     * 
     * @param name the name of the variable
     * @param value the current value
     */
    private Map<String, Integer> prevValues = new HashMap<String, Integer>(0);
    public void logOnChange(String name, int value) {
        if (name == "" || name == " " || name == null) {
            return;
        }
        if (prevValues.get(name) == null) {
            prevValues.put(name, 0);
        }

        if ((value != prevValues.get(name)) && (prevValues.get(name) != null)) {
            log(name + " has updated to: " + value);
        }
        prevValues.put(name, value);
    }

    /**
     * Prints a message to the console only when it changes
     * 
     * @param name the name of the variable
     * @param value the current value
     */
    private Map<String, Boolean> prevValues2 = new HashMap<String, Boolean>();
    public void logOnChange(String name, Boolean value) {
        if (name == "" || name == " " || name == null) {
            return;
        }

        if (value != prevValues2.get(name)) {
            log(name + " has updated to: " + value);
        }
        prevValues2.put(name, value);
    }

    /**
     * Displays a value on the SmartDashboard screen
     * 
     * @param label the label of the value to be displayed
     * @param value the value to display
     */
    public void updateSmartDashboard(String label, Boolean value) {
        SmartDashboard.putBoolean(label, value);
    }
    public void updateSmartDashboard(String label, Double value) {
        SmartDashboard.putNumber(label, value);
    }
    public void updateSmartDashboard(String label, String value) {
        SmartDashboard.putString(label, value);
    }

    /**
     * Turns logging messages for a messenger on
     */
    public void enable() {
        m_enabled = true;
        System.out.println("logging messages turned on for " + m_messenger);
    }

    /**
     * Turns logging messages for a messenger off
     */
    public void disable() {
        m_enabled = false;
        System.out.println("logging messages turn off for " + m_messenger);
    }

    /**
     * Is the debugger enabled?
     */
    public boolean isEnabled() {
        return m_enabled;
    }
}

