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
 * MAG3110 is a digital 3-axis magnetometer from which the data can be read using interfaces contained in this
 * interface.
 * <p>
 * This class wraps the native functions of the sensor.so library with JNA.
 * </p>
 * 
 * @author Thomas Kistel
 * @see Documentation: http://www.element14.com/community/community/designcenter/mems-sensor-board
 */
public interface MAG3110 extends Library {
    
    public static MAG3110 INSTANCE = (MAG3110) Native.loadLibrary("sensor", MAG3110.class);
    
    // Initialize
    /**
     * Initialize MAG3110.
     * 
     * @return 0 – fail, 1 – success
     */
    public short MAG3110_Init();
    
    /**
     * Deinitialize MAG3110.
     */
    public void MAG3110_DeInit();
    
    // Read raw data
    /**
     * Read x-axis data from MAG3110.
     * 
     * @return Raw data for x-axis.
     */
    public int MAG3110_ReadRawData_x();
    
    /**
     * Read y-axis data from MAG3110.
     * 
     * @return Raw data for y-axis.
     */
    public int MAG3110_ReadRawData_y();
    
    /**
     * Read x-axis data from MAG3110.
     * 
     * @return Raw data for z-axis.
     */
    public int MAG3110_ReadRawData_z();
    
    // others
    public int MAG3110_WRITE_REGISTER(char reg, char val);
    public char MAG3110_READ_REGISTER(char reg);
    public int MAG3110_BULK_READ(char reg, char count, char[] data);
    public char MAG3110_ReadRawData(char[] data);
    public int MAG3110_ReadAsInt();
}
