package org.devscite.Model;

import java.util.Calendar;

public class Car extends Vehicle {
    private Integer fare;
    private CarModel carModel;

    public Car(String placa, Calendar horaLLegada, Calendar horaSalida, Integer fare, CarModel carModel ) {
        super(placa, horaLLegada, horaSalida);
        this.fare = fare;
        this.carModel = carModel;
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
