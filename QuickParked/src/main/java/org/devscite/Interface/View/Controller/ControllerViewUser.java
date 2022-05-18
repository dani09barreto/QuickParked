package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.devscite.structure.Controller.ControllerParking;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerViewUser extends RealTimeUpdateView<ControllerParking> implements Initializable {
    public final static String MAIN_FXML_NAME = "../filesFXML/SceneLogin.fxml";
    public final static String WINDOW_NAME = "QuickParked";
    public final static String ICON_NAME = "../img/logo_mini.png";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField passwordField;

    @FXML
    private TextField userField;

    @FXML
    void logIn(ActionEvent event) {

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
