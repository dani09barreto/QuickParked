package org.devscite.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Model.Vehicle;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerViewParking {

    private final static String ICON_NAME = "../img/Logo3.png";
    private final static String MAIN_FXML_NAME = "../Aditionalsfmxl/PaymentScene.fxml";
    private final static String MODIFY_FXML_NAME = "../Aditionalsfmxl/ModifyScene.fxml";
    private final static String STYLE_SHEET_NAME = "../styles.css";
    private final static String WINDOW_NAME = "Generar Pago";
    private ControllerParking controllerParking = new ControllerParking();

    @FXML
    private TableColumn<?, ?> columCarModel;

    @FXML
    private TableColumn<?, ?> columCheckin;

    @FXML
    private TableColumn<?, ?> columFare;

    @FXML
    private TableColumn<?, ?> columLicensePlate;

    @FXML
    private TableColumn<?, ?> columPlace;

    @FXML
    private TableView<?> tableVehicle;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnGeneratePayment;

    @FXML
    private Button btnModify;

    @FXML
    private MenuButton carModel;

    @FXML
    private TextField textLicensePlate;

    @FXML
    private ToggleGroup type_vehicle;


    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void generatePayment(ActionEvent event) {
        ControllerParking controllerParking = new ControllerParking();
        Vehicle car = new Car("GZT687", Calendar.getInstance(), CarModel.Camioneta);
        controllerParking.getControllerVehicle().getVehiclelist().put(car.getLicensePlate(), car);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_FXML_NAME));
            Parent root = (Parent) loader.load();
            ControllerViewPayment controllerPayment = loader.getController();
            controllerPayment.getControllerParking().getControllerVehicle().setVehiclelist(controllerParking.getControllerVehicle().getVehiclelist());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET_NAME).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ICON_NAME))));
            stage.setTitle(WINDOW_NAME);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modifyVehicle(ActionEvent event) {
        ControllerParking controllerParking = new ControllerParking();
        Vehicle car = new Car("GZT546", Calendar.getInstance(), CarModel.Camioneta);
        controllerParking.getControllerVehicle().getVehiclelist().put(car.getLicensePlate(), car);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(MODIFY_FXML_NAME));
            Parent root = (Parent) loader.load();
            ControllerModifyVehicle controllerModify = loader.getController();
            controllerModify.getControllerParking().getControllerVehicle().setVehiclelist(controllerParking.getControllerVehicle().getVehiclelist());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET_NAME).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ICON_NAME))));
            stage.setTitle(WINDOW_NAME);
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ControllerParking getControllerParking() {
        return controllerParking;
    }

}
