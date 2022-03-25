package org.devscite.Model;

import org.devscite.Utils.Exeptions.InvalidLicensePlate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MotorCycle extends Vehicle {
    private final static String plateFormat = "[a-zA-Z]{3}[0-9]{2}[a-zA-Z]";

    public MotorCycle(String licensePlate, Calendar checkin) throws InvalidLicensePlate {
        super(licensePlate, checkin);
        this.fare = 30;
        this.typeVehicle = "Moto";
    }
    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    @Override
    public void calculatePrace() {
        this.checkout = Calendar.getInstance();
        Long dateinMils = this.checkin.getTimeInMillis();
        Long dateOutMils = this.checkout.getTimeInMillis();
        Integer minuts = (int) (Math.abs(dateOutMils - dateinMils) / (1000 * 60));
        Integer prace = this.fare * minuts;
        this.price = prace;
    }
    @Override
    public boolean checkPlate(String licensePlate) {
        return licensePlate.matches(plateFormat);
    }

    @Override
    public String toString() {
        SimpleDateFormat timein = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat timeout = new SimpleDateFormat("hh:mm:ss");
        return "Moto \n" +
                       "tarifa" + fare + "\n" +
                       "idVehicle:" + idVehicle + "\n" +
                       "placa" + licensePlate + "\n" +
                       "hora ingreso" + timein.format(checkin.getTime()) + "\n" +
                       "Precio: " + this.price;
    }

}