package org.devscite.Interface.View.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.devscite.Entities.Model.Employee;
import org.devscite.Entities.Model.UserParking;
import org.devscite.Utils.AlertUtils;
import org.devscite.structure.Controller.ControllerParking;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class ControllerViewWorkersRealTime extends RealTimeObservableView implements Initializable {
    public final static String MAIN_FXML_NAME = "filesFXML/listWorkersScene.fxml";
    public final static String WINDOW_NAME = "Lista de Trabajadores";
    public final static String ICON_NAME = "img/logo_mini.png";

    @FXML
    private Button backBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<UserParking, Integer> celColum;

    @FXML
    private TableColumn<UserParking, Integer> documentColum;

    @FXML
    private TableColumn<UserParking, String> nameColum;

    @FXML
    private TableView<UserParking> workersTable;

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
        workersTable.getItems().addAll(ControllerParking.getInstance().getControllerUser().getiUserDAO().listWorkers());
    }

    public void renderTable() {
        ClearTable();
        workersTable.getItems().addAll(ControllerParking.getInstance().getControllerUser().getiUserDAO().listWorkers());
    }

    public void ClearTable() {
        workersTable.getItems().clear();
    }

    @FXML
    public void deleteWorker(javafx.event.ActionEvent actionEvent) {
        try {
            Employee delete = (Employee) workersTable.getSelectionModel().getSelectedItem();
            System.out.println(delete.getName());
            ControllerParking.getInstance().getControllerUser().getiUserDAO().deleteEmployee(delete);
            renderTable();
        } catch (Exception e) {
            AlertUtils.alertError("ERROR", "El usuario no pudo ser eliminado", "Recuerde selecionar un usuario en la tabla");
            e.printStackTrace();
        }
    }
}