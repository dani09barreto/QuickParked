package org.devscite.View.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.CarModel;

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
    private TextArea Platelist;

    @FXML
    private TextField plateIn;

    @FXML
    private Button btnModify;

    @FXML
    private ComboBox<CarModel> VehicleType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VehicleType.getItems().setAll(CarModel.values());
    }

    @FXML
    void MakeModify(ActionEvent event) {

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void back(ActionEvent event) {

    }
}
