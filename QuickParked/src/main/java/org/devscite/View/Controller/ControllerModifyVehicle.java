package org.devscite.View.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.devscite.Controller.ControllerParking;

public class ControllerModifyVehicle {
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
    private ChoiceBox<?> VehicleTypes;

    @FXML
    void MakeModify(ActionEvent event) {
        try{

        }catch (){
            
        }
    }

    @FXML
    void back(ActionEvent event) {

    }
}
