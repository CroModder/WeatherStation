/*******************************************************************************
 * (c) 2015 Technische Hochschule Wildau
 * (University of Applied Sciences Wildau)
 * Author: Thomas Kistel
 * All rights reserved
 ******************************************************************************/
package de.tk.sensor.core;

import de.tk.sensor.xtrinsic.Accelerometer;
import de.tk.sensor.xtrinsic.Magnetometer;
import de.tk.sensor.xtrinsic.MultiSensor;
import de.tk.sensor.xtrinsic.driver.Bcm2835;


/**
 * Factory class to create the instances of the sensor classes. This factory
 * creates the following sensor classes:
 * <ul>
 * <li>Accelerometer</li>
 * <li>Magnetometer</li>
 * <li>MultiSensor</li>
 * <ul>
 * <p>
 * The {@link SensorFactory} is a singleton to ensure that the native driver
 * will not be initialized more than once.
 * </p>
 * 
 * @author Thomas Kistel
 */
public class SensorFactory {
    
    private static final SensorFactory INSTANCE = new SensorFactory();
    
    private IMagnetometer magnetometer;
    private IAccelerometer accelerometer;
    private IMultiSensor multiSensor;
    
    private SensorFactory() {
        checkOs();
        Bcm2835.INSTANCE.bcm2835_init();
    }
    
    private void checkOs() {
        String osName = System.getProperty("os.name");
        String requiredOs = "linux";
        if(!osName.regionMatches(true, 0, requiredOs, 0, requiredOs.length())) {
            throw new Error("This library only works on linux-arm systems. OS-Name: " + osName);
        }
    }

    /**
     * Returns the singleton instance of the {@link SensorFactory}.
     *  
     * @return the singleton instance of the {@link SensorFactory}.
     */
    public static SensorFactory getInstance() {
        return INSTANCE;
    }
    
    /**
     * Returns the {@link Magnetometer}.
     * 
     * @return the magnetometer
     */
    public IMagnetometer getMagnetometer() {
        if(magnetometer == null)
            magnetometer = new Magnetometer();
        return magnetometer;
    }
    
    /**
     * Returns the {@link Accelerometer}.
     * 
     * @return the accelerometer
     */
    public IAccelerometer getAccelerometer() {
        if(accelerometer == null)
            accelerometer = new Accelerometer();
        return accelerometer;
    }
    
    /**
     * Returns the {@link MultiSensor}.
     * 
     * @return the multi sensor
     */
    public IMultiSensor getMultiSensor() {
        if(multiSensor == null)
            multiSensor = new MultiSensor();
        return multiSensor;
    }
}
