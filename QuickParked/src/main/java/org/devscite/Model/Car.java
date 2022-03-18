package org.devscite.Model;

import java.util.Calendar;

public class Car extends Vehicle {
    private Integer fare;
    private CarModel carModel;

    public Car(String licensePlate, Calendar checkin, Calendar checkout) {
        super(licensePlate, checkin, checkout);
    }


    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public CarModel getTipoCarro() {
        return carModel;
    }

    public void setTipoCarro(CarModel carModel) {
        this.carModel = carModel;
    }
}
