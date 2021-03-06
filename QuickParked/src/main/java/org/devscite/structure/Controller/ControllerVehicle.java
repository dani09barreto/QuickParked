package org.devscite.structure.Controller;

import org.devscite.Entities.Enums.SlotType;
import org.devscite.Entities.Model.Vehicle;
import org.devscite.Utils.Exeptions.ParkingFull;
import org.devscite.Utils.Exeptions.VehicleNotExist;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ControllerVehicle {
    /* ---  Private attributes --- */

    /**
     * Map with all parked vehicles, Key is licensePlate and Value is the Vehicle class
     */
    private Map<String, Vehicle> vehiclelist = new HashMap<>();

    /**
     * Map with all recently paid vehicles
     */
    private Map<String, Vehicle> paidVehiclelist = new HashMap<>();


    /* ---  Public methods --- */

    /**
     * Add a vehicle to the parking system
     *
     * @param vehicle Vehicle to insert
     * @return False if vehicle already exists, True if vehicle was inserted correctly
     * @throws ParkingFull There are no parking slots available
     */
    public boolean addVehicle(Vehicle vehicle) throws ParkingFull {
        // Check if it already exists
        if (vehiclelist.containsKey(vehicle.getLicensePlate().toUpperCase()))
            return false;

        // Assign a parking slot
        vehicle.setParkingPlace(this.parkingSlotAssignment());
        this.vehiclelist.put(vehicle.getLicensePlate().toUpperCase(), vehicle);
        return true;
    }

    /**
     * Search and Fetch a reference to an existing vehicle in the system
     *
     * @param licensePlate Vehicle license plate
     * @return Vehicle reference or NULL on non-existing vehicles
     */
    public Vehicle getVehicle(String licensePlate) {
        if (vehiclelist.containsKey(licensePlate.toUpperCase())) {
            return vehiclelist.get(licensePlate.toUpperCase());
        }
        return null;
    }

    /**
     * Get a random available parking slot
     *
     * @return Integer representing a parking slot
     */
    public Integer parkingSlotAssignment() throws ParkingFull {
        Random randomizer = new Random(System.currentTimeMillis());
        int slot = randomizer.nextInt(ControllerParking.max_parking_slots + 1);

        // Array with assigned slots
        Set<Integer> assignedSlots = this.vehiclelist.values().stream()
                                             .map(Vehicle::getParkingPlace)
                                             .collect(Collectors.toSet());

        // Si los slots contienen todos los valores posibles del 0 al max, Entonces el parqueadero est?? lleno
        if (assignedSlots.containsAll(
                IntStream.rangeClosed(0, ControllerParking.max_parking_slots).boxed().collect(Collectors.toSet()))) {
            throw new ParkingFull();
        }

        // If slot is contained within assignedSlots
        while (assignedSlots.contains(slot)) {
            slot = randomizer.nextInt(ControllerParking.max_parking_slots + 1);
        }
        return slot;
    }

    /**
     * Returns an IMMUTABLE copy of the vehicles currently parked
     *
     * @return IMMUTABLE map of vehicles
     */
    public Map<String, Vehicle> getVehicles() {
        return Collections.unmodifiableMap(this.vehiclelist);
    }

    /**
     * Generate a vehicle payment
     *
     * @param licenseplate License plate of the vehicle
     * @return Checked out vehicle
     * @throws VehicleNotExist Vehicle was not found in the system
     */
    public Vehicle generatePayment(String licenseplate) throws VehicleNotExist {
        Vehicle vehicle = getVehicle(licenseplate.toUpperCase());
        if (vehicle == null) {
            throw new VehicleNotExist("No existe el veh??culo");
        }
        vehicle.setCheckout(Calendar.getInstance());
        vehicle.calculatePrice();


        // Update lists
        vehiclelist.remove(licenseplate.toUpperCase());
        paidVehiclelist.put(licenseplate.toUpperCase(), vehicle);

        return vehicle;
    }
}
