package de.tk.sensor.core;

/**
 * Interface for a MultiSensor to retrieve the following data:
 * <ul>
 * <li>Temperatur in Celcius degree (°C)</li>
 * <li>Altitude data in meters (m)</li>
 * <li>Pressure in Pascals (Pa)</li>
 * </ul>
 * 
 * @author Thomas Kistel
 */
public interface IMultiSensor extends IDriver {

    /**
     * Sets the MPL3115A2 into Alt-Mode. In this mode temperature and altitude data can be received.
     */
    public abstract void initAlt();

    /**
     * Sets the MPL3115A2 into Alt-Mode. In this mode temperature and altitude data can be received.
     */
    public abstract void initBar();

    /**
     * Get and returns altitude data from the sensor. The raw integer data value is converted into a double
     * value according to the driver specification.
     * <p>
     * Altitude data is only received when the sensor is in active and alt-mode.
     * </p>
     * 
     * @return the altitude value in meters (m)
     */
    public abstract double getAlt();

    /**
     * Get and returns pressure data from the sensor. The raw integer data value is converted into a double
     * value according to the driver specification.
     * <p>
     * Pressure data is only received when the sensor is in active and bar-mode.
     * </p>
     * 
     * @return the pressure value in Pascals (Pa)
     */
    public abstract double getBar();

    /**
     * Get and returns temperature data from the sensor. The raw integer data value is converted into a double
     * value according to the driver specification.
     * <p>
     * Temperature data is only received when the sensor is in active mode.
     * </p>
     * 
     * @return the temperature value in Celcius degree (°C)
     */
    public abstract double getTemp();

}