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

    public void setVehiclelist(Map<String, Vehicle> vehiclelist) {
        this.vehiclelist = vehiclelist;
    }

    public void setPaidVehiclelist(Map<String, Vehicle> paidVehiclelist) {
        this.paidVehiclelist = paidVehiclelist;
    }

    public Vehicle vehicleExist (String licensePlate){
        if (vehiclelist.containsKey(licensePlate)){
            return vehiclelist.get(licensePlate);
        }
        return null;
    }
    public Map<String, Vehicle> addVehiclePaid (Vehicle vehicle){
        if (!paidVehiclelist.containsKey(vehicle.getLicensePlate())){
            paidVehiclelist.put(vehicle.getLicensePlate(), vehicle);
        }
        return this.paidVehiclelist;
    }
    public Map<String, Vehicle> eliminateVehicle (String licencePlate){
        if (vehiclelist.containsKey(licencePlate)){
            vehiclelist.remove(licencePlate);
        }
        return this.vehiclelist;
    }
}
