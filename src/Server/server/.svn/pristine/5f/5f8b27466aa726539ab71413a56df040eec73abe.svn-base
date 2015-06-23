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
 * The MPL3115A2 features three kinds of modes, 8 different sample rates, 16 different acquisition time steps
 * (1 second to 9 hours), and compensated direct reading of pressure (20 bit in Pascal) or altitude (20 bit in
 * meters). The driver provides the following interfaces for implementing these features.
 * <p>
 * <b>Operation Modes</b> MPL3115A2 has three operation modes: Standby, Active Altitude, and Active Barometer.
 * These modes can be implemented using the following interfaces.
 * </p>
 * <p>
 * <b>Over Sampling</b> Output Sample Rate can be set as shown in Table 10 - System Output Data Rate
 * Selection. Table 10 and 11 contain the functions used for configuring over-sampling parameters.
 * </p>
 * <p>
 * <b>Data Acquisition</b> Pressure (20 bit in Pascals), Altitude (20 bit in meters), and Temperature (12 bit
 * in degrees Celsius) can be read by functions contained in the following tables, and be calculated using the
 * formulas in section 0.
 * </p>
 * <p>
 * This class wraps the native functions of the sensor.so library with JNA.
 * </p>
 * 
 * @author Thomas Kistel
 * @see Documentation: http://www.element14.com/community/community/designcenter/mems-sensor-board
 */
public interface MPL3115A2 extends Library {
    
    public static MPL3115A2 INSTANCE = (MPL3115A2) Native.loadLibrary("sensor", MPL3115A2.class);
    
    // Modes of Operation
    /**
     * Put MPL3115A2 into Active Mode.
     */
    public void MPL3115A2_Active();

    /**
     * Put MPL3115A2 into Standby Mode.
     * 
     * @return The value of CTRL_REG1 before modification
     */
    public short MPL3115A2_Standby();

    /**
     * Initialize MPL3115A2 for Alt mode.
     * 
     * @return 0 – fail, 1 – success
     */
    public short MPL3115A2_Init_Alt();

    /**
     * Initialize MPL3115A2 for Bar mode
     * 
     * @return 0 – fail, 1 – success
     */
    public short MPL3115A2_Init_Bar();

    // Over sample
    /**
     * Change the OSR Ratio.
     * <p>
     * Output Sample Rate can be set as shown in the table - System Output Data Rate Selection.
     * <table border=1>
     * <tr>
     * <th>OSR</th>
     * <th>Oversample Ratio</th>
     * <th>Minimum Time Between Data Samples</th>
     * </tr>
     * <tr align=center>
     * <td>0</td>
     * <td>1</td>
     * <td>2.5 ms</td>
     * </tr>
     * <tr align=center>
     * <td>1</td>
     * <td>2</td>
     * <td>5 ms</td>
     * </tr>
     * <tr align=center>
     * <td>2</td>
     * <td>4</td>
     * <td>10 ms</td>
     * </tr>
     * <tr align=center>
     * <td>3</td>
     * <td>8</td>
     * <td>20 ms</td>
     * </tr>
     * <tr align=center>
     * <td>4</td>
     * <td>16</td>
     * <td>40 ms</td>
     * </tr>
     * <tr align=center>
     * <td>5</td>
     * <td>32</td>
     * <td>80 ms</td>
     * </tr>
     * <tr align=center>
     * <td>6</td>
     * <td>64</td>
     * <td>160 ms</td>
     * </tr>
     * <tr align=center>
     * <td>7</td>
     * <td>128</td>
     * <td>320 ms</td>
     * </tr>
     * </table>
     * </p>
     * 
     * @param osr OSR Ratio
     */
    public void MPL3115A2_SetOSR(short osr);

    /**
     * Change sample step.
     * 
     * @param stepTime Sample Step = 2^step;
     */
    public void MPL3115A2_SetStepTime(short stepTime);

    // Read raw data
    /**
     * Read Altitude or Barometer data from MPL3115A2. If {@link #MPL3115A2_Init_Alt()}
     * was called, this method returns Altitude data. If {@link #MPL3115A2_Init_Bar()}
     * was called, this method returns Barometer data.
     * 
     * @return The raw data for Altitude or Barometer.
     */
    public int MPL3115A2_Read_Alt();

//    /**
//     * Read Barometer data from MPL3115A2.
//     * 
//     * @return The raw data for Barometer.
//     */
//    public int MPL3115A2_Read_Bar();

    /**
     * Read Temperature data from MPL3115A2.
     * 
     * @return The raw data for temperature.
     */
    public int MPL3115A2_Read_Temp();

    // others
    public void MPL3115A2_WRITE_REGISTER(short reg, short value);

    public short MPL3115A2_READ_REGISTER(short reg);
}
