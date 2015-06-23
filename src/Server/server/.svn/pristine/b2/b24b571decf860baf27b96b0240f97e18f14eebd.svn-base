/*******************************************************************************
 * (c) 2015 Technische Hochschule Wildau
 * (University of Applied Sciences Wildau)
 * Author: Thomas Kistel
 * All rights reserved
 ******************************************************************************/
package de.tk.sensor.xtrinsic.driver;

import com.sun.jna.Library;
import com.sun.jna.Native;


/**
 * Class to initialize the BCM2835 driver that is required by the sensor library and all other driver classes
 * in this package.
 * <p>
 * This class wraps the native functions of the sensor.so library with JNA.
 * </p>
 * 
 * @author Thomas Kistel
 */
public interface Bcm2835 extends Library {
    
    public static Bcm2835 INSTANCE = (Bcm2835) Native.loadLibrary("sensor", Bcm2835.class);
    
    /**
     * Initialised the BCM2835 driver. This method should be called before using the sensors.
     * 
     * @return 0 – fail, 1 – success
     */
    public int bcm2835_init();
}
