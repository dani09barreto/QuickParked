package org.devscite.View.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.Vehicle;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.Exeptions.VehicleNotExist;

import java.text.SimpleDateFormat;
import java.util.Map;

public class ControllerViewPayment {
    private ControllerParking controllerParking = new ControllerParking();

    @FXML
    private Button btnBack;

    @FXML
    private Button btnPay;

    @FXML
    private Button btn_Search;

    @FXML
    private Label labelCarModel;

    @FXML
    private Label labelCehckOut;

    @FXML
    private Label labelCheckIn;

    @FXML
    private Label labelLicensePlate;

    @FXML
    private Label labelPrice;

    @FXML
    private TextField textLicensePlate;

    @FXML
    private TextField textValue;

    @FXML
    void searchLicensePlate(ActionEvent event) {
        searchVehicle(event);
    }

    @FXML
    void searchVehicle(ActionEvent event) {
        SimpleDateFormat timein = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat timeout = new SimpleDateFormat("hh:mm:ss");
        String licensePlate = textLicensePlate.getText();

        try {
            Vehicle vehicle = controllerParking.generatePayment(licensePlate);
            labelLicensePlate.setText(vehicle.getLicensePlate());
            if (vehicle instanceof Car){
                labelCarModel.setText(((Car) vehicle).getTipoCarro().name());
            }
            else
                labelCarModel.setText("N.A");
            labelCheckIn.setText(timein.format(vehicle.getCheckin().getTime()));
            labelCehckOut.setText(timeout.format(vehicle.getCheckout().getTime()));
            labelPrice.setText(String.valueOf(vehicle.getPrice()));

        } catch (VehicleNotExist e){
            e.printStackTrace();
            AlertUtils.alertError("Error", "Error el vehiculos solicitado no existe", "Pruebe con otra Placa");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void payServiceText(ActionEvent event) {
        payService(event);
    }

    @FXML
    void payService(ActionEvent event) {
        String licensePlate = textLicensePlate.getText();
        Vehicle vehicle = controllerParking.getControllerVehicle().vehicleExist(licensePlate);
        try {
            if (vehicle == null){
                throw new VehicleNotExist("el vehiculo no existe");
            }
            Map<String, Vehicle> payVehicleslistTemp = controllerParking.getControllerVehicle().addVehiclePaid(vehicle);
            Map<String, Vehicle> vehicleslistTemp = controllerParking.getControllerVehicle().eliminateVehicle(vehicle.getLicensePlate());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ParkedScene.fxml"));
            Parent root = (Parent) loader.load();
            ControllerViewParking controllerPayment = loader.getController();
            controllerPayment.getControllerParking().getControllerVehicle().setPaidVehiclelist(payVehicleslistTemp);
            controllerPayment.getControllerParking().getControllerVehicle().setVehiclelist(vehicleslistTemp);

        }catch (VehicleNotExist e){
            e.getMessage();
            AlertUtils.alertError("Error", "Error el vehiculos solicitado no existe", "Pruebe con otra Placa");
        }
        catch (Exception e){
            e.getMessage();
        }
        Integer value = Integer.valueOf(textValue.getText());
        AlertUtils.alertConfirmation("Pago realizado", "El pago del Vehiculo con placas: "+ vehicle.getLicensePlate()+ "fue exitoso\n"+ "Cambio: "+ (value - vehicle.getPrice()),"");
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void back(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public ControllerParking getControllerParking() {
        return controllerParking;
    }
}
