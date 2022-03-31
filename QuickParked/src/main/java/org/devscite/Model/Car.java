package org.devscite.Model;

import org.devscite.Utils.Exeptions.InvalidLicensePlate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Car extends Vehicle {

    private static final String plateFormat = "[a-zA-Z]{3}[0-9]{3}";

    private CarModel carModel;

    public Car(String licensePlate, Calendar checkin, CarModel carModel) throws InvalidLicensePlate {

        super(licensePlate, checkin);
        this.carModel = carModel;

        if (this.carModel == CarModel.Automovil) {
            this.rate = 40;
        }
        if (this.carModel == CarModel.Camioneta) {
            this.rate = 60;
        }
        if (this.carModel == CarModel.Furgon) {
            this.rate = 75;
        }
        if (this.carModel == CarModel.Electrico) {
            this.rate = 80;
        }
    }

    @Override
    public boolean invalidPlate(String licensePlate) {
        return !licensePlate.matches(plateFormat);
    }

    @Override
    public String getModel() {
        return carModel.name();
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        return "Vehicle : Car\n" +
                       "Rate: " + rate + "\n" +
                       "Car model: " + carModel + "\n" +
                       "License plate: " + licensePlate + "\n" +
                       "Checkin hour: " + time.format(checkin.getTime()) + "\n" +
                       "Checkout hour: " + time.format(checkout.getTime()) + '\n' +
                       "Price: " + this.price + '\n';
    }

    @Override
    public String getTypeVehicle() {
        return "Carro";
    }
}
