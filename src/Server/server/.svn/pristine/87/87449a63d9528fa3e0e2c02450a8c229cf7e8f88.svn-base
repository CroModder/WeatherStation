/*******************************************************************************
 * (c) 2015 Technische Hochschule Wildau
 * (University of Applied Sciences Wildau)
 * Author: Thomas Kistel
 * All rights reserved
 ******************************************************************************/
package de.tk.sensor.core;

/**
 * Driver Classes that implements this interface can expose additional interfaces or classes (i.e. functionality),
 * when required via the {@link #getDriver(Class)} method. This interface also provides methods to enable and
 * disable the sensor.
 * <p>
 * Implementing sensor classes expose their driver class to access the native functions of the sensor driver.
 * </p>
 * 
 * @author Thomas Kistel
 */
public interface IDriver {

    /**
     * Returns an object which is an instance of the given class associated with this object. Returns
     * <code>null</code>, if no such object can be found.
     * <p>
     * The default implementation returns <code>null</code>.
     * </p>
     * 
     * @param driverClass the adapter class to look up
     * @return a object of the given class, or <code>null</code> if this object does not have an adapter for
     *         the given class
     */
    public default <T> T getDriver(Class<T> driverClass) { 
        return null;
    };

    /**
     * Enables the sensor to retrieve data. The default implementation does nothing.
     */
    public default void enable() {};

    /**
     * Disables the sensor to stop receiving data. The default implementation does nothing.
     */
    public default void disable() {};
}
