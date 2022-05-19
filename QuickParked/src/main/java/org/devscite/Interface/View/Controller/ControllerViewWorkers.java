package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.devscite.Entities.Model.User;
import org.devscite.structure.Controller.ControllerParking;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ControllerViewWorkers extends RealTimeUpdateView<ControllerParking> implements Initializable {
    public final static String MAIN_FXML_NAME = "../filesFXML/listWorkersScene.fxml";
    public final static String WINDOW_NAME = "Lista de Trabajadores";
    public final static String ICON_NAME = "../img/logo_mini.png";

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<User, Integer> celColum;

    @FXML
    private TableColumn<User, Integer> documentColum;

    @FXML
    private TableColumn<User, UUID> idColum;

    @FXML
    private TableColumn<User, String> nameColum;

    @FXML
    private TableView<User> workersTable;

    @Override
    public void onUpdate() {

    }

    @Override
    public void initState() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}