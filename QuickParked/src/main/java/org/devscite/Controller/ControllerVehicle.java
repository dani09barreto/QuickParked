package org.devscite.Controller;

import org.devscite.Model.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControllerVehicle {
    private Map<UUID, Vehicle> vehiclelist = new HashMap<>();

    public Map<UUID, Vehicle> getVehiclelist() {
        return vehiclelist;
    }
}
