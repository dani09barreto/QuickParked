package org.devscite.Model;

import java.util.Calendar;

public class MotorCycle extends Vehicle {
    private Integer fare;
    public MotorCycle(String placa, Calendar horaLLegada, Calendar horaSalida, Integer fare) {
        super(placa, horaLLegada, horaSalida);
        this.fare = fare;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }
}