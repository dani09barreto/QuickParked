package org.devscite.Utils.Exeptions;

/**
 * Parking has no available space left
 */
public class ParkingFull extends Exception {

    public ParkingFull() {
        super("Parqueadero lleno");
    }

    public ParkingFull(String msg) {
        super(msg);
    }
}
