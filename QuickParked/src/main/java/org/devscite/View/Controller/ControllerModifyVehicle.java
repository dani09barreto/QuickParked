package org.devscite.View.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Model.MotorCycle;
import org.devscite.Model.Vehicle;
import org.devscite.Utils.AlertUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerModifyVehicle implements Initializable {
    private ControllerParking controllerParking = new ControllerParking();

    @FXML
    private Button btnBack;

    @FXML
    private ListView<String> PlateList;

    @FXML
    private TextField plateIn;

    @FXML
    private Button btnCharge;
    @FXML
    private Button btnModify;

    @FXML
    private ComboBox<CarModel> VehicleType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VehicleType.getItems().setAll(CarModel.values());
    }

    @FXML
    void ChargeList(ActionEvent event) {
        PlateList.getItems().setAll(controllerParking.getControllerVehicle().getVehiclelist().keySet());
        VehicleType.setDisable(false);
    }

    @FXML
    void MakeModify(ActionEvent event) throws IOException {
        try {
            String placaModificar = PlateList.getSelectionModel().getSelectedItem();
            Vehicle v = controllerParking.getControllerVehicle().vehicleExist(placaModificar);
            if (v instanceof Car || v instanceof MotorCycle) {
                if (v.checkPlate(plateIn.getText())) {
                    if (v instanceof Car && VehicleType.getValue() != null) {
                        Vehicle CarTemp = new Car(plateIn.getText(), v.getCheckin(), VehicleType.getValue());
                        controllerParking.getControllerVehicle().eliminateVehicle(v.getLicensePlate());
                        controllerParking.getControllerVehicle().addVehicle(CarTemp);
                    } else if (v instanceof MotorCycle) {
                        Vehicle MotoTemp = new MotorCycle(plateIn.getText(), v.getCheckin());
                        controllerParking.getControllerVehicle().eliminateVehicle(v.getLicensePlate());
                        controllerParking.getControllerVehicle().addVehicle(MotoTemp);
                        if (VehicleType.getValue() != null)
                            AlertUtils.alertWarning("WARNING", "ERROR EN LA CATEGORIA DEL VEHICULO", "No puedes cambiar de motocicleta a vehiculo.");
                    }
                    Map<String, Vehicle> VehicleListTemp = this.controllerParking.getControllerVehicle().getVehiclelist();
                    System.out.println("lista retornada");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../ParkedScene.fxml"));
                    Parent root = (Parent) loader.load();
                    ControllerViewParking controllerModifyVehicle = loader.getController();
                    controllerModifyVehicle.getControllerParking().getControllerVehicle().setVehiclelist(VehicleListTemp);
                    System.out.println(controllerModifyVehicle.getControllerParking().getControllerVehicle().getVehiclelist());

                    AlertUtils.alertInformation("Modificacion hecha!", "Se ha realizado de forma exitosa la modificacion.", "");
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                } else {
                    AlertUtils.alertError("ERROR", "PLACA INVALIDA", "Digita una placa valida");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "El vehiculo no se ha podido modificar.", "Vuelve a intentarlo");
        }
    }

    @FXML
    void back(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public ControllerParking getControllerParking() {
        return controllerParking;
    }
}
