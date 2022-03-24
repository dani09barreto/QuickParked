package org.devscite.Controller;

import org.devscite.Model.Vehicle;
import org.devscite.Utils.Exeptions.VehicleNotExist;

import java.util.Calendar;
import java.util.Scanner;

public class ControllerParking {

    // Slots de parqueadero TODO: Que el usuario pueda cambiarlo
    static final Integer max_parking_slots = 10;

    private ControllerVehicle controllerVehicle = new ControllerVehicle();

    public ControllerVehicle getControllerVehicle() {
        return controllerVehicle;
    }

    public Vehicle generatePayment(String licenseplate) throws VehicleNotExist {
        Vehicle vehicle = controllerVehicle.vehicleExist(licenseplate.toUpperCase());
        if (vehicle == null) {
            throw new VehicleNotExist("No existe Vehiculo");
        }
        vehicle.setCheckout(Calendar.getInstance());
        vehicle.calculatePrace();
        System.out.println();
        return vehicle;
    }
}
