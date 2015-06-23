/*******************************************************************************
 * (c) 2015 Technische Hochschule Wildau
 * (University of Applied Sciences Wildau)
 * Author: Thomas Kistel
 * All rights reserved
 ******************************************************************************/
package de.tk.sensor.xtrinsic;

import de.tk.sensor.core.IAccelerometer;
import de.tk.sensor.xtrinsic.driver.MMA8491Q;
import de.tk.sensor.xtrinsic.driver.MMA8491Q.RawData;

/**
 * Class that encapsulates the MMA8491Q driver to retrieve the following data:
 * <ul>
 * <li>3-axis accelerometer data in mg</li>
 * </ul>
 * <p>
 * The driver has the following operating modes:
 * <ol>
 * <li>Initialized and Enabled: Required to retrieve data.</li>
 * <li>Des-Enabled: in this state, no data can be received.</li>
 * </ol>
 * </p>
 * <p>
 * Clients should prefer to use this class instead of the {@link MMA8491Q} driver class. However if client
 * require to configure specific setting like the OSR or StepTime, they can receive the driver class with the
 * {@link #getDriver(Class)} method.
 * </p>
 * 
 * @author Thomas Kistel
 */
public class Accelerometer implements IAccelerometer {

    private MMA8491Q driver;


    /**
     * Creates an instance of a {@link Accelerometer} and initializes and enables the sensor.
     */
    public Accelerometer() {
        driver = MMA8491Q.INSTANCE;
        driver.MMA8491Q_Init();
        driver.MMA8491Q_Enable();
    }

    /**
     * Returns the driver class {@link MMA8491Q}, if the <code>adapter</code> parameter is of this class.
     */
    @Override
    public <T> T getDriver(Class<T> adapter) {
        if (adapter.isAssignableFrom(MMA8491Q.class)) {
            @SuppressWarnings("unchecked")
            T result = (T)driver;
            return result;
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IDriver#enable()
     */
    @Override
    public void enable() {
        driver.MMA8491Q_Enable();
    }
    
    /*
     * (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IDriver#disable()
     */
    @Override
    public void disable() {
        driver.MMA8491Q_DisEnable();
    }

    /* (non-Javadoc)
     * @see de.tk.sensor.xtrinsic.core.IAccelerometer#getData()
     */
    @Override
    public AxisData getData() {
        // Important: call enable before read, otherwise we do not receive data
        driver.MMA8491Q_Enable();
        RawData[] data = new RawData[1];
        driver.MMA8491_Read(data);
        return new AxisData(data[0].x, data[0].y, data[0].z);
    }
}
