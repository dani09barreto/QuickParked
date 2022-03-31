package org.devscite.Model;

import org.devscite.Utils.Exeptions.InvalidLicensePlate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MotorCycle extends Vehicle {

    private final static String plateFormat = "[a-zA-Z]{3}[0-9]{2}[a-zA-Z]";

    public MotorCycle(String licensePlate, Calendar checkin) throws InvalidLicensePlate {
        super(licensePlate, checkin);
        this.rate = 30;
    }

    @Override
    public boolean invalidPlate(String licensePlate) {
        return !licensePlate.matches(plateFormat);
    }

    @Override
    public String toString() {
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        return "Vehicle : Motorcycle\n" +
                       "Rate: " + rate + "\n" +
                       "License plate: " + licensePlate + "\n" +
                       "Checkin hour: " + time.format(checkin.getTime()) + "\n" +
                       "Checkout hour: " + time.format(checkout.getTime()) + '\n' +
                       "Price: " + this.price + '\n';
    }

    @Override
    public String getTypeVehicle() {
        return "Moto";
    }
}