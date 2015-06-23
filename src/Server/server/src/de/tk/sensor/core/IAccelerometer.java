package de.tk.sensor.core;


/**
 * Interface for an Accelerometer to retrieve the following data:
 * <ul>
 * <li>3-axis accelerometer data in mg</li>
 * </ul>
 * 
 * @author Thomas Kistel
 */
public interface IAccelerometer extends IDriver {

    /**
     * Retrieves an returns the axis (x, y, z) data from the accelerometer.
     * 
     * @return the axis (x, y, z) data
     */
    public abstract AxisData getData();
    
    /**
     * Class to store the x, y, z axis data of the accelerometer.
     */
    public class AxisData {

        private int x;
        private int y;
        private int z;


        public AxisData(int x, int y, int z) {
            this.x = x;

            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        @Override
        public String toString() {
            return "Accelerometer[x=" + x + ", y=" + y + ", z=" + z + "]";
        }
    }
}