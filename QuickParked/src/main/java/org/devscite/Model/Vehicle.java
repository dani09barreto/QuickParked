package org.devscite.Model;

import java.util.Calendar;
import java.util.UUID;

public abstract class Vehicle {
    protected UUID idVehicle;
    protected String licensePlate;
    protected Calendar checkin;
    protected Calendar checkout;
    protected long price;
    protected Integer parkingPlace;

    public Vehicle(String licensePlate, Calendar checkin) {
        this.idVehicle = UUID.randomUUID();
        this.licensePlate = licensePlate;
        this.checkin = checkin;
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

    public void setIdVehicle(UUID idVehicle) {
        this.idVehicle = idVehicle;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Integer getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(Integer parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public abstract void calculatePrace ();
}
