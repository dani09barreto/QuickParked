package org.devscite.Controller;

import org.devscite.Model.Vehicle;
import org.devscite.Utils.Exeptions.VehicleNotExist;

import java.util.Calendar;
import java.util.Scanner;

public class ControllerParking {
    private ControllerVehicle controllerVehicle = new ControllerVehicle();

    public ControllerVehicle getControllerVehicle() {
        return controllerVehicle;
    }

    public Vehicle generatePayment (String licenseplate) throws VehicleNotExist {
        Vehicle vehicle = controllerVehicle.vehicleExist(licenseplate);
        if (vehicle == null) {
            throw new VehicleNotExist("No existe Vehiculo");
        }
        vehicle.setCheckout(Calendar.getInstance());
        vehicle.calculatePrace();
        System.out.println();
        return vehicle;
    }
}
