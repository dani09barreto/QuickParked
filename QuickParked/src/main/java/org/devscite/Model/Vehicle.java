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

    public String getCheckin() {
        SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss");
        return date.format(checkin.getTime());
    }

    public void setCheckin(Calendar checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss");
        return date.format(checkout.getTime());
    }

    public void setCheckout(Calendar checkout) {
        this.checkout = checkout;
    }
}
