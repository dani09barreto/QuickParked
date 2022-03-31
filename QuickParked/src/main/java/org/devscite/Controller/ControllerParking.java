package org.devscite.Controller;

import org.devscite.Model.Vehicle;
import org.devscite.Utils.Exeptions.VehicleNotExist;

import java.util.Calendar;

public class ControllerParking {

    // Slots de parqueadero TODO: Que el usuario pueda cambiarlo
    static final Integer max_parking_slots = 60;

    /**
     * Controller that handles all vehicles
     */
    private final ControllerVehicle controllerVehicle = new ControllerVehicle();

    /**
     * Get the vehicle controller
     *
     * @return ControllerVehicle class
     */
    public ControllerVehicle getControllerVehicle() {
        return controllerVehicle;
    }
}
