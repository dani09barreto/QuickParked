package org.devscite.Entities.Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Parking {
    private String name;
    private String ubication;
    private Calendar schedule;
    private ArrayList<Space> spaces = new ArrayList<>();
    private Integer rateCar;
    private Integer rateCycle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public Calendar getSchedule() {
        return schedule;
    }

    public void setSchedule(Calendar schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    public Integer getRateCar() {
        return rateCar;
    }

    public void setRateCar(Integer rateCar) {
        this.rateCar = rateCar;
    }

    public Integer getRateCycle() {
        return rateCycle;
    }

    public void setRateCycle(Integer rateCycle) {
        this.rateCycle = rateCycle;
    }
}
