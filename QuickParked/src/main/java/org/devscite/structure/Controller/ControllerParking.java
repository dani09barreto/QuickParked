package org.devscite.structure.Controller;

import org.devscite.Entities.Model.Employee;
import org.devscite.Entities.Model.UserParking;

import java.util.Calendar;

public class ControllerParking {
    private Employee actualUser;
    private Calendar timeSesion;
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

    public Employee getActualUser() {
        return actualUser;
    }

    public void loginUser(Employee use){
        this.actualUser = use;
        this.timeSesion = Calendar.getInstance();

    }

    public Calendar getTimeSesion() {
        return timeSesion;
    }
}
