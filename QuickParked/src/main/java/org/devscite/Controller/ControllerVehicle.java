package org.devscite.Controller;

import org.devscite.Model.Vehicle;

import java.util.HashMap;
import java.util.Locale;
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

    public Vehicle vehicleExist(String licensePlate) {
        if (vehiclelist.containsKey(licensePlate.toUpperCase())) {
            return vehiclelist.get(licensePlate.toUpperCase());
        }
        return null;
    }

    public Map<String, Vehicle> addVehiclePaid(Vehicle vehicle) {
        if (!paidVehiclelist.containsKey(vehicle.getLicensePlate().toUpperCase())) {
            paidVehiclelist.put(vehicle.getLicensePlate().toUpperCase(), vehicle);
        }
        return this.paidVehiclelist;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehiclelist.containsKey(vehicle.getLicensePlate().toUpperCase()))
            return false;
        this.vehiclelist.put(vehicle.getLicensePlate().toUpperCase(), vehicle);
        return true;
    }

    public Map<String, Vehicle> eliminateVehicle(String licencePlate) {
        vehiclelist.remove(licencePlate.toUpperCase());
        return this.vehiclelist;
    }
}
