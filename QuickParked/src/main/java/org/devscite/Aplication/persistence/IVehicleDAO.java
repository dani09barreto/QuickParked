package org.devscite.Aplication.persistence;

import org.devscite.Entities.Model.Vehicle;

import java.util.ArrayList;

public interface IVehicleDAO {
    void addVehicle(Vehicle user);
    void updateVehicle(Vehicle user);
    void deleteVehicle(Vehicle user);
    ArrayList<Vehicle> listVehicle ();
}
