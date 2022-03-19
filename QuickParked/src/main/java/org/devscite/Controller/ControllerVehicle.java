package org.devscite.Controller;

import org.devscite.Model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class ControllerVehicle {
    private Map<String, Vehicle> vehiclelist = new HashMap<>();
    private Map<String, Vehicle> paidVehiclelist = new HashMap<>();

    public Map<String, Vehicle> getVehiclelist() {
        return vehiclelist;
    }

    public Map<String, Vehicle> getPaidVehiclelist() {
        return paidVehiclelist;
    }
    public Vehicle vehicleExist (String licensePlate){
        if (vehiclelist.containsKey(licensePlate)){
            return vehiclelist.get(licensePlate);
        }
        return null;
    }
    public void addVehiclePaid (Vehicle vehicle){
        if (!paidVehiclelist.containsKey(vehicle.getLicensePlate())){
            paidVehiclelist.put(vehicle.getLicensePlate(), vehicle);
        }
    }
    public void eliminateVehicle (String licencePlate){
        if (vehiclelist.containsKey(licencePlate)){
            vehiclelist.remove(licencePlate);
        }
    }
}
