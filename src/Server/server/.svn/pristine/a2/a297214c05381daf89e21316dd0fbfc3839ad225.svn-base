/*******************************************************************************
 * (c) 2015 Technische Hochschule Wildau
 * (University of Applied Sciences Wildau)
 * Author: Thomas Kistel
 * All rights reserved
 ******************************************************************************/
package de.tk.sensor.xtrinsic;

import de.tk.sensor.core.IMagnetometer;
import de.tk.sensor.xtrinsic.driver.MAG3110;
import de.tk.sensor.xtrinsic.driver.MPL3115A2;

/**
 * Class that encapsulates the MAG3110 driver to retrieve the following data:
 * <ul>
 * <li>3-axis (x, y, z) magnetometer data in Micro Tesla (µT)</li>
 * </ul>
 * <p>
 * The driver has the following operating modes:
 * <ol>
 * <li>Initialized: Required to retrieve data.</li>
 * <li>De-Initialized: in this state, no data can be received.</li>
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
public class Magnetometer implements IMagnetometer {

    private MAG3110 driver;
    // x    y   z
    // -565 700 0
    // -576 224 0
    // -521 977 0
    private CalibrationData calibrationData = new CalibrationData(-775, 1124, 0);
    
    /**
     * Creates an instance of a {@link Magnetometer} and initializes the sensor.
     */
    public Magnetometer() {
        driver = MAG3110.INSTANCE;
        driver.MAG3110_Init();
    }
    
    /**
     * Returns the driver class {@link MAG3110}, if the <code>adapter</code> parameter is of this class.
     */
    @Override
    public <T> T getDriver(Class<T> adapter) {
        if (adapter.isAssignableFrom(MAG3110.class)) {
            @SuppressWarnings("unchecked")
            T result = (T)driver;
            return result;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMagnetometer#getOrientation()
     */
    @Override
    public int getOrientation() {
        int x = driver.MAG3110_ReadRawData_x();
        int y = driver.MAG3110_ReadRawData_y();
        int y_off = calibrationData.getYOff();
        int x_off = calibrationData.getXOff();

        double orientation = Math.atan2((y - y_off), (x - x_off)) * 180 / Math.PI + 180;
        return (int) orientation;
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMagnetometer#getX()
     */
    @Override
    public int getX() {
        return driver.MAG3110_ReadRawData_x() / 40;
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMagnetometer#getY()
     */
    @Override
    public int getY() {
        return driver.MAG3110_ReadRawData_y() / 40;
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IMagnetometer#getZ()
     */
    @Override
    public int getZ() {
        return driver.MAG3110_ReadRawData_z() / 40;
    }  
    
    /*
     * (non-Javadoc)
     * @see de.tk.sensor.core.IMagnetometer#getCalibrationData()
     */
    public CalibrationData getCalibrationData() {
        return calibrationData;
    }
    
    /*
     * (non-Javadoc)
     * @see de.tk.sensor.core.IMagnetometer#setCalibrationData(de.tk.sensor.core.IMagnetometer.CalibrationData)
     */
    public void setCalibrationData(CalibrationData calibrationData) {
        if(calibrationData == null)
            throw new NullPointerException("CalibrationData must not be null");
        this.calibrationData = calibrationData;
    }
}
