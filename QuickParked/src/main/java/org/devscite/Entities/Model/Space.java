package org.devscite.Entities.Model;

import org.devscite.Entities.Enums.CarModel;

import java.util.UUID;

public class Space {
    private UUID id;
    private Boolean availability;
    private CarModel typeVehicle;

    public Space(UUID id, Boolean availability, CarModel typeVehicle) {
        this.id = UUID.randomUUID();
        this.availability = false;
        this.typeVehicle = typeVehicle;
    }
}
