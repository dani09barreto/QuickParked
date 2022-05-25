package org.devscite.structure.Controller;

public class ControllerParking {

    // Slots de parqueadero TODO: Que el usuario pueda cambiarlo
    static final Integer max_parking_slots = 60;

    // Instancia del Singleton
    private static ControllerParking instance = null;

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

    private ControllerParking() {
    }

    /**
     * Get a Singleton instance
     *
     * @return Unique Singleton instance
     */
    public static ControllerParking getInstance() {
        if (instance == null) {
            instance = new ControllerParking();
        }

        return instance;
    }
}
