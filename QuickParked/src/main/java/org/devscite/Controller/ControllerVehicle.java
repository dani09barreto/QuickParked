package org.devscite.Controller;

import org.devscite.Model.Vehicle;
import org.devscite.Utils.Exeptions.ParkingFull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ControllerVehicle {
    private Map<String, Vehicle> vehiclelist = new HashMap<>();

    private Map<String, Vehicle> paidVehiclelist = new HashMap<>();

    public Map<String, Vehicle> getVehiclelist() {
        return vehiclelist;
    }

    public Map<String, Vehicle> getPaidVehiclelist() {
        return paidVehiclelist;
    }

    public void setVehiclelist(Map<String, Vehicle> vehiclelist) {
        this.vehiclelist = vehiclelist;
    }

    public void setPaidVehiclelist(Map<String, Vehicle> paidVehiclelist) {
        this.paidVehiclelist = paidVehiclelist;
    }

    public Vehicle vehicleExist(String licensePlate) {
        if (vehiclelist.containsKey(licensePlate.toUpperCase())) {
            return vehiclelist.get(licensePlate.toUpperCase());
        }
        return null;
    }

    public Map<String, Vehicle> addVehiclePaid(Vehicle vehicle) {
        if (!paidVehiclelist.containsKey(vehicle.getLicensePlate().toUpperCase())) {
            paidVehiclelist.put(vehicle.getLicensePlate().toUpperCase(), vehicle);
        }
        return this.paidVehiclelist;
    }

    /**
     * Assign a random parkingSlot
     *
     * @return Integer representing parkingSlot
     * <p>
     * TODO: Maybe create a parkingSlot controller and a parkingSlot class to represent different types of parking slots
     */
    public Integer parkingSlotAssignment() throws ParkingFull {
        Random randomizer = new Random(System.currentTimeMillis());
        int slot = randomizer.nextInt(ControllerParking.max_parking_slots + 1);

        // Array with assigned slots
        Set<Integer> assignedSlots = this.vehiclelist.values().stream()
                .map(Vehicle::getParkingPlace)
                .collect(Collectors.toSet());

        // Si los slots contienen todos los valores posibles del 0 al max, Entonces el parqueadero está lleno
        if (assignedSlots.containsAll(
                IntStream.rangeClosed(0, ControllerParking.max_parking_slots).boxed().collect(Collectors.toSet()))) {
            throw new ParkingFull();
        }

        // If slot is contained within assignedSlots
        while (assignedSlots.contains(slot)) {
            slot = randomizer.nextInt(ControllerParking.max_parking_slots + 1);
            System.out.println("Looking for a parking slot");
        }

        return slot;
    }

    public boolean addVehicle(Vehicle vehicle) throws ParkingFull {
        if (vehiclelist.containsKey(vehicle.getLicensePlate().toUpperCase()))
            return false;

        // Assign a parking slot
        vehicle.setParkingPlace(this.parkingSlotAssignment());
        this.vehiclelist.put(vehicle.getLicensePlate().toUpperCase(), vehicle);
        return true;
    }

    public Map<String, Vehicle> eliminateVehicle(String licencePlate) {
        vehiclelist.remove(licencePlate.toUpperCase());
        return this.vehiclelist;
    }
}
