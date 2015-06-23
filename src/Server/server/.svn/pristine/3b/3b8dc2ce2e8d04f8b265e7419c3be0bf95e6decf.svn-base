/*******************************************************************************
 * (c) 2015 Technische Hochschule Wildau
 * (University of Applied Sciences Wildau)
 * Author: Thomas Kistel
 * All rights reserved
 ******************************************************************************/
package de.tk.sensor.xtrinsic.driver;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

/**
 * The MMA8491Q is a low voltage, 3-axis low-g accelerometer housed in a 3 mm by 3 mm QFN package. The device
 * can accommodate two accelerometer configurations, acting as either an easy to implement 45° Tilt Sensor or
 * a digital (I2C) output accelerometer. In the 45° Tilt Sensor mode, it offers extremely easy board
 * implementation by using a single line of output per axis. In the digital output mode, 14-bit ±8g raw data
 * can be read from the device with high 1 mg/LSB sensitivity. The extreme low power capabilities of the
 * MMA8491Q reduce the low data rate current consumption to less than 400 nA per Hz.
 * <p>
 * This class wraps the native functions of the sensor.so library with JNA.
 * </p>
 * 
 * @author Thomas Kistel
 * @see Documentation: http://www.element14.com/community/community/designcenter/mems-sensor-board
 */
public interface MMA8491Q extends Library {
    
    public class RawData extends Structure {
        
        public short x;
        public short y;
        public short z;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(new String[] {"x", "y", "z"});
        }
        
        @Override
        public String toString() {
            return "RawData[x="+x+",y="+y+",z="+z+"]";
        }
    }

    public static MMA8491Q INSTANCE = (MMA8491Q)Native.loadLibrary("sensor", MMA8491Q.class);


    // Initialize
    public short MMA8491Q_Init();

    public short MMA8491Q_Enable();

    public short MMA8491Q_DisEnable();

    // Read raw data
    public int MMA8491_Read(RawData[] xyz);

    // others
    public void MMA8491Q_WRITE_REGISTER();

    public void MMA8491Q_READ_REGISTER();

    public int MMA8491Q_BULK_READ(char a, char b, char[] c);

    public int MMA8491_ReadRaw(char[] data);
}
