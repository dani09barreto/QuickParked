package org.devscite.Controller;

import org.devscite.Model.Vehicle;

import java.util.Scanner;

public class ControllerParked {
    private ControllerVehicle controllerVehicle = new ControllerVehicle();

    public ControllerVehicle getControllerVehicle() {
        return controllerVehicle;
    }

    public Integer generatePayment (String licenseplate, Integer payment) {
        Scanner in = new Scanner(System.in);
        Vehicle vehicle = controllerVehicle.vehicleExist(licenseplate);
        if (vehicle == null) {
            System.out.println("Vehiculo no existe");
            return 0;
        }
        System.out.println(vehicle.toString());
        System.out.println("Ingrese el monto a pagar: ");
        int monto = in.nextInt();

        System.out.println("Vueltas: " + (monto - vehicle.calculatePrace()));

        controllerVehicle.getVehiclelist().remove(vehicle.getLicensePlate());
        controllerVehicle.addVehiclePaid(vehicle);
        return 0;
    }
}
