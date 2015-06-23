/*******************************************************************************
 * (c) 2015 Technische Hochschule Wildau
 * (University of Applied Sciences Wildau)
 * Author: Thomas Kistel
 * All rights reserved
 ******************************************************************************/
package de.tk.sensor.xtrinsic;

import de.tk.sensor.core.IMultiSensor;
import de.tk.sensor.xtrinsic.driver.MPL3115A2;

/**
 * Class that encapsulates the MPL3115A2 driver to retrieve the following data:
 * <ul>
 * <li>Temperatur in Celcius degree (°C)</li>
 * <li>Altitude data in meters (m)</li>
 * <li>Pressure in Pascals (Pa)</li>
 * </ul>
 * <p>
 * The driver has the following operating modes:
 * <ol>
 * <li>Active: Required to retrieve data.</li>
 * <li>Standby: in this state, no data can be received</li>
 * <li>Alt-Mode: This mode can be initialized with the method {@link #initAlt()}. In this mode temperature and
 * altitude data can be received.</li>
 * <li>Bar-Mode: This mode can be initialized with the method {@link #initBar()}. In this mode temperature and
 * pressure data can be received.</li>
 * </ol>
 * </p>
 * <p>
 * Clients should prefer to use this class instead of the {@link MPL3115A2} driver class. However if client
 * require to configure specific setting like the OSR or StepTime, they can receive the driver class with the
 * {@link #getDriver(Class)} method.
 * </p>
 * 
 * @author Thomas Kistel
 */
public class MultiSensor implements IMultiSensor {

    private MPL3115A2 driver;


    /**
     * Creates an instance of a {@link MultiSensor} and sets the sensor into the active mode.
     */
    public MultiSensor() {
        driver = MPL3115A2.INSTANCE;
        enable();
    }

    /**
     * Returns the driver class {@link MPL3115A2}, if the <code>adapter</code> parameter is of this class.
     */
    @Override
    public <T> T getDriver(Class<T> adapter) {
        if (adapter.isAssignableFrom(MPL3115A2.class)) {
            @SuppressWarnings("unchecked")
            T result = (T)driver;
            return result;
        }
        return null;
    }
    
    /**
     * Put MPL3115A2 into Active Mode.
     * 
     * @see de.tk.sensor.core.IDriver#enable()
     */
    @Override
    public void enable() {
        driver.MPL3115A2_Active();
    }
    
    /**
     * Put MPL3115A2 into Standby Mode.
     * 
     * @see de.tk.sensor.core.IDriver#disable()
     */
    @Override
    public void disable() {
        driver.MPL3115A2_Standby();
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMultiSensor#initAlt()
     */
    @Override
    public void initAlt() {
        driver.MPL3115A2_Init_Alt();
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMultiSensor#initBar()
     */
    @Override
    public void initBar() {
        driver.MPL3115A2_Init_Bar();
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMultiSensor#getAlt()
     */
    @Override
    public double getAlt() {
        int v = driver.MPL3115A2_Read_Alt();
        int alt_m = v >> 8;
        int alt_l = v & 0xff;
        if ((alt_m & 0x8000) > Short.MAX_VALUE)
            alt_m = alt_m - 65536;
        double frac = alt_l / 256.0;
        return alt_m + frac;
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMultiSensor#getBar()
     */
    @Override
    public double getBar() {
        int v = driver.MPL3115A2_Read_Alt();
        int alt_m = v >> 6;
        int alt_l = v & 0x30;
        return alt_m + alt_l / 64.0;
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMultiSensor#getTemp()
     */
    @Override
    public double getTemp() {
        int t = driver.MPL3115A2_Read_Temp();
        int t_m = (t >> 8) & 0xFF;
        int t_l = t & 0xFF;
        if (t_m > 0x7f)
            t_m = t_m - 256;
        return t_m + t_l / 256.0;
    }
}
