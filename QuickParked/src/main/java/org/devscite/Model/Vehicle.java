package org.devscite.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public abstract class Vehicle {
    protected UUID idVehicle;
    protected String licensePlate;
    protected Calendar checkin;
    protected Calendar checkout;

    public Vehicle(String licensePlate, Calendar checkin, Calendar checkout) {
        this.idVehicle = UUID.randomUUID();
        this.licensePlate = licensePlate;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public UUID getIdVehicle() {
        return idVehicle;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Calendar getCheckin() {
        return checkin;
    }

    public Calendar getCheckout() {
        return checkout;
    }

    public void setCheckin(Calendar checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Calendar checkout) {
        this.checkout = checkout;
    }

    public abstract Integer calculatePrace ();
}
