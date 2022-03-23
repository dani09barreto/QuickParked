package org.devscite.View.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Model.MotorCycle;
import org.devscite.Model.Vehicle;
import org.devscite.Utils.AlertUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerModifyVehicle implements Initializable {
    private ControllerParking controllerParking = new ControllerParking();

    public ControllerParking getControllerParking() {
        return controllerParking;
    }

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
    void MakeModify(ActionEvent event) {
        try {
            for (Vehicle v : controllerParking.getControllerVehicle().getVehiclelist().values()) {
                if (v.getLicensePlate().equals(this.PlateList.getSelectionModel().getSelectedItem())) {
                    if (v instanceof Car || v instanceof MotorCycle) {
                        if (v.checkPlate(plateIn.getText())) {
                            this.controllerParking.getControllerVehicle().getVehiclelist().get(v.getLicensePlate()).setLicensePlate(plateIn.getText());
                            if (v instanceof Car) {
                                ((Car) v).setCarModel(VehicleType.getValue());
                            }else if(v instanceof MotorCycle) {
                                AlertUtils.alertWarning("WARNING","ERROR EN LA CATEGORIA DEL VEHICULO","No puedes cambiar de motocicleta a vehiculo.");
                            }
                        }else{
                            AlertUtils.alertError("ERORR","PLACA INVALIDA","Digita una placa valida");
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.alertError("ERROR", "El vehiculo no se ha podido modificar.", "Vuelve a intentarlo");
        }
        System.out.println(this.getControllerParking().getControllerVehicle().getVehiclelist().values());
    }

    @FXML
    void back(ActionEvent event) {

    }
}
