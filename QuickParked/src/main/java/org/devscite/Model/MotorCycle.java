package org.devscite.Model;

import java.util.Calendar;

public class MotorCycle extends Vehicle {
    private Integer fare;

    public MotorCycle(String licensePlate, Calendar checkin, Calendar checkout, Integer fare) {
        super(licensePlate, checkin, checkout);
        this.fare = fare;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }
}