package org.devscite.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Car extends Vehicle {
    private Integer fare;
    private CarModel carModel;

    public Car(String licensePlate, Calendar checkin, Calendar checkout, CarModel carModel) {
        super(licensePlate, checkin, checkout);
        this.carModel = carModel;
        if (this.carModel == CarModel.Automovil){
            this.fare = 40;
        }
        if (this.carModel == CarModel.Camioneta){
            this.fare = 60;
        }
        if (this.carModel == CarModel.Furgon){
            this.fare = 75;
        }
        if (this.carModel == CarModel.Electrico){
            this.fare = 80;
        }
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

    @Override
    public Integer calculatePrace() {
        this.checkout = Calendar.getInstance();
        Long dateinMils = this.checkin.getTimeInMillis();
        Long dateOutMils = this.checkin.getTimeInMillis();
        Integer minuts = (int) (Math.abs(dateOutMils - dateinMils)/ (1000*60));
        Integer prace = this.fare * minuts;
        return prace;
    }

    @Override
    public String toString() {
        SimpleDateFormat timein = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat timeout = new SimpleDateFormat("hh:mm:ss");
        return "Car \n" +
                "tarifa" + fare + "\n"+
                "Tipo de carro:" + carModel + "\n"+
                "idVehicle:" + idVehicle + "\n"+
                "placa" + licensePlate + "\n"+
                "hora ingreso" + timein.format(checkin.getTime()) + "\n"+
                "hora salida" + timeout.format(checkin.getTime()) + "\n" +
                "Precio: " + calculatePrace();
    }
}
