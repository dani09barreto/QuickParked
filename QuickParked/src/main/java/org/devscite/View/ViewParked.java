package org.devscite.View;

import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Model.Vehicle;

import java.util.Calendar;

public class ViewParked {
    private ControllerParking controllerParking = new ControllerParking();
    public static void main( String[] args ) {
        ViewParked viewParked = new ViewParked();
        //Vehicle carro = new Car("GBM677", Calendar.getInstance(), Calendar.getInstance(), CarModel.Automovil);
        //viewParked.controllerParking.getControllerVehicle().getVehiclelist().put(carro.getLicensePlate(), carro);
        //viewParked.controllerParking.generatePayment(carro.getLicensePlate(), 20000);
    }
}
