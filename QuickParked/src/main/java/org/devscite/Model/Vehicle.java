package org.devscite.Model;

import org.devscite.Utils.Exeptions.InvalidLicensePlate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Vehicle {

    protected String licensePlate;
    protected Calendar checkin;
    protected Calendar checkout;
    protected long price;
    protected Integer parkingPlace;
    protected Integer rate = 0;

    public Vehicle(String licensePlate, Calendar checkin) throws InvalidLicensePlate {
        // Check if plate is correct
        if (this.invalidPlate(licensePlate)) {
            throw new InvalidLicensePlate(licensePlate);
        }

        this.licensePlate = licensePlate.toUpperCase();
        this.checkin = checkin;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) throws InvalidLicensePlate {
        if (this.invalidPlate(licensePlate.toUpperCase())) throw new InvalidLicensePlate(licensePlate);
        this.licensePlate = licensePlate;
    }

    public Calendar getCheckin() {
        return checkin;
    }

    public void setCheckin(Calendar checkin) {
        this.checkin = checkin;
    }

    public String getCheckinFormatted() {
        SimpleDateFormat Fecha = new SimpleDateFormat("hh:mm:ss aa");
        return Fecha.format(checkin.getTime());
    }

    public Calendar getCheckout() {
        return checkout;
    }

    public void setCheckout(Calendar checkout) {
        this.checkout = checkout;
    }

    public long getPrice() {
        return price;
    }

    public Integer getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(Integer parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer newrate) {
        rate = newrate;
    }

    public String getModel() {
        return "N.A";
    }

    public void calculatePrice() {
        this.checkout = Calendar.getInstance();
        Long dateinMils = this.checkin.getTimeInMillis();
        Long dateOutMils = this.checkout.getTimeInMillis();
        long minutes = (dateOutMils - dateinMils) / 60000;
        this.price = this.rate * minutes;
    }

    public String getTypeVehicle() {
        return this.getClass().getSimpleName();
    }

    /**
     * Checks if a plate is valid, should be overriden by Vehicles types
     *
     * @param licensePlate LicensePlate to check
     * @return True if valid
     */
    public abstract boolean invalidPlate(String licensePlate);
}
