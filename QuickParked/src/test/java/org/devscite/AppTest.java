package org.devscite;

import org.devscite.Entities.Enums.CarModel;
import org.devscite.Entities.Model.*;
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
    public void insertionCar() {
        try {
            Vehicle temp = new Car("AAA123", Calendar.getInstance(), CarModel.Automovil);
            ControllerParking.getInstance().getControllerVehicle().addVehicle(temp);
            assertFalse(ControllerParking.getInstance().getControllerVehicle().addVehicle(temp));
        } catch (InvalidLicensePlate | ParkingFull e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void vehicleList() {
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
    public void slotAssigment() {
        try {
            assertTrue(0 < ControllerParking.getInstance().getControllerVehicle().parkingSlotAssignment());
        } catch (ParkingFull e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchVehicle() {
        try {
            Vehicle testingVehicle = new Car("ABC123", Calendar.getInstance(), CarModel.Automovil);
            ControllerParking.getInstance().getControllerVehicle().addVehicle(testingVehicle);
            Vehicle expected = ControllerParking.getInstance().getControllerVehicle().getVehicle("ABC123");
            // Hay que borrar para que luego el test de cantidad de vehículos no falle
            ControllerParking.getInstance().getControllerVehicle().generatePayment("ABC123");

            assertEquals(testingVehicle, expected);

        } catch (ParkingFull | InvalidLicensePlate | VehicleNotExist e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchEmployee() {
        try {
            Employee temp = new Employee("test_user", "test123", "TEST USER",
                    BigDecimal.valueOf(1234567890), BigDecimal.valueOf(987654321));
            ControllerParking.getInstance().getControllerUser().getiUserDAO().addUser(temp);
            ControllerParking.getInstance().getControllerUser().getiUserDAO().addEmployee(temp);
            Employee user = (Employee) ControllerParking.getInstance().getControllerUser().getiUserDAO()
                                               .searchUser("test_user", "test123");
            ControllerParking.getInstance().getControllerUser().getiUserDAO().deleteEmployee(temp);
            ControllerParking.getInstance().getControllerUser().getiUserDAO().deleteUser(temp);

            // Verificar que sean iguales excepto el ID que es aleatorio
            assertEquals(temp.getDocument(), user.getDocument());
            assertEquals(temp.getName(), user.getName());
            assertEquals(temp.getNumber(), user.getNumber());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchUser() {
        try {
            Employee temp = new Employee("test_user", "test123", "TEST USER",
                    BigDecimal.valueOf(1234567890), BigDecimal.valueOf(987654321));
            ControllerParking.getInstance().getControllerUser().getiUserDAO().addUser(temp);
            UserParking user = ControllerParking.getInstance().getControllerUser().getiUserDAO()
                                       .searchUser("test_user", "test123");
            ControllerParking.getInstance().getControllerUser().getiUserDAO().deleteUser(temp);

            // Verificar que sus propiedades son iguales
            assertEquals(temp.getUsername(), user.getUsername());
            assertEquals(temp.getPassWord(), user.getPassWord());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = InvalidLicensePlate.class)
    public void invalidLicensePlate() throws InvalidLicensePlate {
        new Car("123ABC", Calendar.getInstance(), CarModel.Automovil);
        new MotorCycle("A1B2C3", Calendar.getInstance());
    }

    @Test
    public void uppercaseLicensePlate() throws InvalidLicensePlate {
        String plate = new Car("abc123", Calendar.getInstance(), CarModel.Automovil).getLicensePlate();
        assertEquals("ABC123", plate);

        plate = new MotorCycle("abc12a", Calendar.getInstance()).getLicensePlate();
        assertEquals("ABC12A", plate);
    }

    @Test
    public void singletonTest() {
        ControllerParking controller1 = ControllerParking.getInstance();
        ControllerParking controller2 = ControllerParking.getInstance();
        assertEquals(controller1, controller2);
    }

    @Test
    public void loginTest() {
        Employee temp = new Employee("test_user", "test123", "TEST USER",
                BigDecimal.valueOf(1234567890), BigDecimal.valueOf(987654321));
        ControllerParking.getInstance().loginUser(temp);

        Employee user = ControllerParking.getInstance().getActualUser();
        assertEquals(user, temp);
    }
}
