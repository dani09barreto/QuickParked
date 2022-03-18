package org.devscite.Model;

import java.util.Calendar;

public class Vehicle {
    protected String licensePlate;
    protected Calendar checkin;
    protected Calendar checkout;

    public Vehicle(String licensePlate, Calendar checkin, Calendar checkout) {
        this.licensePlate = licensePlate;
        this.checkin = checkin;
        this.checkout = checkout;
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

    public void setCheckin(Calendar checkin) {
        this.checkin = checkin;
    }

    public Calendar getCheckout() {
        return checkout;
    }

    public void setCheckout(Calendar checkout) {
        this.checkout = checkout;
    }
}
