package org.devscite;

import org.devscite.Entities.Enums.CarModel;
import org.devscite.Entities.Model.Car;
import org.devscite.Entities.Model.Employee;
import org.devscite.Entities.Model.UserParking;
import org.devscite.Entities.Model.Vehicle;
import org.devscite.Utils.Exeptions.InvalidLicensePlate;
import org.devscite.Utils.Exeptions.ParkingFull;
import org.devscite.Utils.Exeptions.VehicleNotExist;
import org.devscite.structure.Controller.ControllerParking;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void InsertionCar() {
        try {
            Vehicle temp = new Car("AAA123", Calendar.getInstance(), CarModel.Automovil);
            ControllerParking.getInstance().getControllerVehicle().addVehicle(temp);
            assertFalse(ControllerParking.getInstance().getControllerVehicle().addVehicle(temp));
        } catch (InvalidLicensePlate | ParkingFull e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void VehicleList() {
        try {
            Vehicle temp = new Car("AAA123", Calendar.getInstance(), CarModel.Automovil);
            ControllerParking.getInstance().getControllerVehicle().addVehicle(temp);
            assertEquals(1, ControllerParking.getInstance().getControllerVehicle().getVehicles().size());
            ControllerParking.getInstance().getControllerVehicle().generatePayment(temp.getLicensePlate());
            assertEquals(0, ControllerParking.getInstance().getControllerVehicle().getVehicles().size());
        } catch (InvalidLicensePlate | VehicleNotExist | ParkingFull e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void slot() {
        try {
            Vehicle temp = new Car("AAA123", Calendar.getInstance(), CarModel.Automovil);
            assertTrue(0 < ControllerParking.getInstance().getControllerVehicle().parkingSlotAssignment());
        } catch (InvalidLicensePlate | ParkingFull e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void SearchEmployee() {
        try {
            UserParking temp = new Employee("Santiago_29@", "Santi263", "Daniel Barreto", BigDecimal.valueOf(10031626), BigDecimal.valueOf(311851313));
            ControllerParking.getInstance().getControllerUser().getiUserDAO().addEmployee((Employee) temp);
            assertEquals(temp, ControllerParking.getInstance().getControllerUser().getiUserDAO().searchUser("Santiago_29@", "Santi263"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SearchUser() {
        System.out.println("hola");
    }
}
