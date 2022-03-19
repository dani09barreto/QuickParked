package org.devscite.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MotorCycle extends Vehicle {
    private Integer fare;

    public MotorCycle(String licensePlate, Calendar checkin, Calendar checkout) {
        super(licensePlate, checkin, checkout);
        this.fare = 30;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
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
        return "Moto \n" +
                "tarifa" + fare + "\n"+
                "idVehicle:" + idVehicle + "\n"+
                "placa" + licensePlate + "\n"+
                "hora ingreso" + timein.format(checkin.getTime()) + "\n"+
                "hora salida" + timeout.format(checkin.getTime()) + "\n";
    }

}