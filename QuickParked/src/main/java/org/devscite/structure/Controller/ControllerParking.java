package org.devscite.structure.Controller;

public class ControllerParking {

    // Slots de parqueadero TODO: Que el usuario pueda cambiarlo
    static final Integer max_parking_slots = 60;

    /**
     * Controller that handles all vehicles
     */
    private final ControllerVehicle controllerVehicle = new ControllerVehicle();
    private final ControllerUser controllerUser = new ControllerUser();
    /**
     * Get the vehicle controller
     *
     * @return ControllerVehicle class
     */
    public ControllerVehicle getControllerVehicle() {
        return controllerVehicle;
    }

    public ControllerUser getControllerUser() {
        return controllerUser;
    }
}
