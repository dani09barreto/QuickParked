package org.devscite.Model;

import org.devscite.Utils.Exeptions.InvalidLicensePlate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Car extends Vehicle {

    private static final String plateFormat = "[a-zA-Z]{3}[0-9]{3}";

    private Integer fare;
    private CarModel carModel;

    public Car(String licensePlate, Calendar checkin) throws InvalidLicensePlate {
        this(licensePlate, checkin, CarModel.Automovil);
    }

    public Car(String licensePlate, Calendar checkin, CarModel carModel) throws InvalidLicensePlate {
        super(licensePlate, checkin);
        this.carModel = carModel;
        if (this.carModel == CarModel.Automovil) {
            this.fare = 40;
        }
        if (this.carModel == CarModel.Camioneta) {
            this.fare = 60;
        }
        if (this.carModel == CarModel.Furgon) {
            this.fare = 75;
        }
        if (this.carModel == CarModel.Electrico) {
            this.fare = 80;
        }
    }

    @Override
    public boolean checkPlate(String licensePlate) {
        return licensePlate.matches(plateFormat);
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public CarModel getTipoCarro() {
        return carModel;
    }

    public void setTipoCarro(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    @Override
    public void calculatePrace() {
        this.checkout = Calendar.getInstance();
        Long dateinMils = this.checkin.getTimeInMillis();
        System.out.println(dateinMils);
        Long dateOutMils = this.checkout.getTimeInMillis();
        System.out.println(dateOutMils);
        long minuts = (dateOutMils - dateinMils) / 60000;
        System.out.println(minuts);
        long prace = this.fare * minuts;
        this.price = prace;
    }

    @Override
    public String toString() {
        SimpleDateFormat timein = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat timeout = new SimpleDateFormat("hh:mm:ss");
        return "Car \n" +
                       "tarifa" + fare + "\n" +
                       "Tipo de carro:" + carModel + "\n" +
                       "idVehicle:" + idVehicle + "\n" +
                       "placa" + licensePlate + "\n" +
                       "hora ingreso" + timein.format(checkin.getTime()) + "\n" +
                       "Precio: " + this.price;
    }
}
