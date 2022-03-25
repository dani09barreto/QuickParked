package org.devscite.Model;

import org.devscite.Utils.Exeptions.InvalidLicensePlate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public abstract class Vehicle {
    protected UUID idVehicle;
    protected String licensePlate;
    protected Calendar checkin;
    protected Calendar checkout;
    protected long price;
    protected Integer parkingPlace;
    protected Integer fare = 0;
    protected String typeVehicle;

    public Vehicle(String licensePlate, Calendar checkin) throws InvalidLicensePlate {
        // Check if plate is correct
        if (!this.checkPlate(licensePlate)) {
            throw new InvalidLicensePlate(licensePlate);
        }
        this.idVehicle = UUID.randomUUID();
        this.licensePlate = licensePlate.toUpperCase();
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

    public String getCheckinString() {
        SimpleDateFormat Fecha = new SimpleDateFormat("hh:mm:ss aa");
        return Fecha.format(checkin.getTime());
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

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public Integer getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(Integer parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public abstract void calculatePrace();

    /**
     * Checks if a plate is valid, should be overriden by Vehicles types
     *
     * @param licensePlate LicensePlate to check
     * @return True if valid
     */
    public abstract boolean checkPlate(String licensePlate);
}
