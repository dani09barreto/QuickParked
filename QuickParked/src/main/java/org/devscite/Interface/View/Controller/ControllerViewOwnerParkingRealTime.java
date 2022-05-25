package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerViewOwnerParkingRealTime extends RealTimeObservableView implements Initializable {

    public final static String MAIN_FXML_NAME = "../filesFXML/OwnerParkingScene.fxml";
    public final static String WINDOW_NAME = "Informacion Parqueadero";
    public final static String ICON_NAME = "../img/logo_mini.png";

    @FXML
    private TextField addressParkingLabel;

    @FXML
    private Button btnAddParking;

    @FXML
    private Button btnAddWorker;

    @FXML
    private Button btnLogOut;

    @FXML
    private TextField celWorkerLabel;

    @FXML
    private TextField comunsSlotsLabel;

    @FXML
    private TextField documentWorkerLabel;

    @FXML
    private TextField electricSlotsLabel;

    @FXML
    private TextField fireCareLabel;

    @FXML
    private TextField fireCycleLabel;

    @FXML
    private Button listWorkers;

    @FXML
    private TextField nameParkingLabel;

    @FXML
    private TextField nameWorkerLabel;

    @FXML
    private PasswordField passWorkerLabel;

    @FXML
    void addInfoParking(ActionEvent event) {

    }

    @FXML
    void addWorker(ActionEvent event) {

    }

    @FXML
    void listWorkers(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void initState() {

    }

    @Override
    public void onExit() {

    }
}
