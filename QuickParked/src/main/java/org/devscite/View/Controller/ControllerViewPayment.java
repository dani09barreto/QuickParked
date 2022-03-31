package org.devscite.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.Vehicle;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.Exeptions.ValueNotValid;
import org.devscite.Utils.Exeptions.VehicleNotExist;

import java.text.SimpleDateFormat;

public class ControllerViewPayment extends RealTimeUpdateView<ControllerParking> {

    public final static String MAIN_FXML_NAME = "../additionalFXML/PaymentScene.fxml";
    public final static String WINDOW_NAME = "Pago de vehículos";
    public final static String ICON_NAME = "../img/logo_mini.png";

    @FXML
    private Button backBtn;

    @FXML
    private Button btnSearch;

    @FXML
    private Label labelCarModel;

    @FXML
    private Label labelCheckIn;

    @FXML
    private Label labelFee;

    @FXML
    private Label labelLicensePlate;

    @FXML
    private Label labelPrice;

    @FXML
    private Button payBtn;

    @FXML
    private TextField textLicensePlate;

    @FXML
    private TextField textValue;

    void resetLabels() {
        this.textValue.setText("");
        this.textLicensePlate.setText("");
        this.labelCheckIn.setText("");
        this.labelFee.setText("");
        this.labelCarModel.setText("");
        this.labelPrice.setText("");
        this.labelLicensePlate.setText("");
    }

    void fillFields(Vehicle vehicle) {
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss aa");

        this.textLicensePlate.setText(vehicle.getLicensePlate());

        labelCheckIn.setText(time.format(vehicle.getCheckin().getTime()));
        labelFee.setText(vehicle.getRate().toString() + " $/min");

        vehicle.calculatePrice();
        labelPrice.setText(vehicle.getPrice() + " $");

        labelLicensePlate.setText(vehicle.getLicensePlate());

        if (vehicle instanceof Car) {
            labelCarModel.setText(vehicle.getModel());
        } else {
            labelCarModel.setText("N.A");
        }
    }

    @FXML
    void payService(ActionEvent event) {
        // Get values from Screen
        String licensePlate = textLicensePlate.getText();
        Vehicle vehicle = manager.getController().getControllerVehicle().getVehicle(licensePlate.toUpperCase());
        int value;

        try {
            value = Integer.parseInt(textValue.getText());

            if (vehicle == null) {
                throw new VehicleNotExist("el vehiculo no existe");
            }

            if (value < 0 || value < vehicle.getPrice()) {
                throw new ValueNotValid("Valor no es valido");
            }

            if (manager.getController().getControllerVehicle().generatePayment(vehicle.getLicensePlate()) == null)
                throw new VehicleNotExist("Error intero, el vehículo no existe");

            notifyUpdate();
            System.out.println("VAL: " + this.labelPrice.getText());
            value -= vehicle.getPrice();

        } catch (VehicleNotExist e) {
            AlertUtils.alertError("Error", "Error: el vehiculo solicitado no existe", "Pruebe con otra Placa");
            return;
        } catch (ValueNotValid | NumberFormatException e) {
            AlertUtils.alertError("Error", "Error: el valor ingresado no es valido", "El pago no es valido, revise el monto");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("Error", "Error: el Pago no pudo ser realizado", "Error: " + e.getMessage());
            return;
        }

        AlertUtils.alertInformation("Información", "Pago exitoso", "El cambio es de: " + value);
        close(event);
    }

    @FXML
    void searchLicensePlate(ActionEvent event) {

        String licensePlate = textLicensePlate.getText().toUpperCase();

        try {
            Vehicle vehicle = manager.getController().getControllerVehicle().getVehicle(licensePlate);
            if (vehicle == null) throw new VehicleNotExist("No existe el vehículo");

            fillFields(vehicle);
        } catch (VehicleNotExist e) {
            AlertUtils.alertError("Error", "Error el vehículo solicitado no existe", "Pruebe con otra Placa");
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("Error", "Se ha producido un error", "Causa desconocida...");
        }
    }

    @Override
    public void onUpdate() {
        resetLabels();
    }

    @Override
    public void initState() {
        resetLabels();
    }

    @Override
    public void onExit() {

    }
}
