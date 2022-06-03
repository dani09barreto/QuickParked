package org.devscite.Entities.Model;

import org.devscite.Entities.Enums.CarModel;

import java.util.UUID;

/**
 * Represents a Parking slot
 */
public class Slot {
    private UUID id;
    private Boolean availability;
    private CarModel typeVehicle;

    public Slot(UUID id, Boolean availability, CarModel typeVehicle) {
        this.id = UUID.randomUUID();
        this.availability = false;
        this.typeVehicle = typeVehicle;
    }
}
