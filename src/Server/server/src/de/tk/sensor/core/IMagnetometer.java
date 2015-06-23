package de.tk.sensor.core;

/**
 * Interface for a Magnetometer to retrieve the following data:
 * <ul>
 * <li>3-axis (x, y, z) magnetometer data in Micro Tesla (µT)</li>
 * </ul>
 * 
 * @author Thomas Kistel
 */
public interface IMagnetometer extends IDriver {

    /**
     * Calibration data object that stores the X, Y and Z coordinates for calibration.
     */
    public class CalibrationData {

        private final int xOff, yOff, zOff;

        public CalibrationData(int xOff, int yOff, int zOff) {
            this.xOff = xOff;
            this.yOff = yOff;
            this.zOff = zOff;
        }

        public int getXOff() {
            return xOff;
        }

        public int getYOff() {
            return yOff;
        }

        public int getZOff() {
            return zOff;
        }
    }


    /**
     * Returns the orientation computed from the x- and y-coordinates.
     * 
     * @return
     */
    public abstract int getOrientation();

    /**
     * Read x-axis data from MAG3110. The raw integer data value is converted into an integer value according
     * to the driver specification.
     * 
     * @return the x-axis data in Micro Tesla (µT)
     */
    public abstract int getX();

    /**
     * Read y-axis data from MAG3110. The raw integer data value is converted into an integer value according
     * to the driver specification.
     * 
     * @return the y-axis data in Micro Tesla (µT)
     */
    public abstract int getY();

    /**
     * Read z-axis data from MAG3110. The raw integer data value is converted into an integer value according
     * to the driver specification.
     * 
     * @return the z-axis data in Micro Tesla (µT)
     */
    public abstract int getZ();

    /**
     * Returns the current configuration of the {@link CalibrationData}.
     * 
     * @return the current configuration of the {@link CalibrationData}
     */
    public abstract CalibrationData getCalibrationData();

    /**
     * Sets a new configuration of calibration data for the sensor. The <code>calibrationData</code> must not
     * be <code>null</code>.
     * 
     * @param calibrationData the new configuration of calibration data
     * @throws NullPointerException if <code>calibrationData</code> is <code>null</code>
     */
    public abstract void setCalibrationData(CalibrationData calibrationData);

}