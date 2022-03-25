package org.devscite.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Model.MotorCycle;
import org.devscite.Model.Vehicle;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.Exeptions.InvalidLicensePlate;
import org.devscite.Utils.Exeptions.ParkingFull;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ResourceBundle;
import static java.awt.SystemColor.control;

public class ControllerViewParking {

    private final static String ICON_NAME = "../img/Logo3.png";
    private final static String MAIN_FXML_NAME = "../Aditionalsfmxl/PaymentScene.fxml";
    private final static String MODIFY_FXML_NAME = "../Aditionalsfmxl/ModifyScene.fxml";
    private final static String STYLE_SHEET_NAME = "../styles.css";
    private final static String WINDOW_NAME = "Generar Pago";
    private final ControllerParking controllerParking = new ControllerParking();

    @FXML
    private TableColumn<Car, CarModel> columCarModel;

    @FXML
    private TableColumn<Vehicle, Calendar> columCheckin;

    @FXML
    private TableColumn<Vehicle, Integer> columFare;

    @FXML
    private TableColumn<Vehicle, String> columLicensePlate;

    @FXML
    private TableColumn<Vehicle, Integer> columPlace;

    @FXML
    private TableView<Vehicle> tableVehicle;

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

    @FXML
    private RadioButton selectedCar;

    @FXML
    private RadioButton selectedMotorcycle;

    @FXML
    public void initialize() {
        initCarTypes();
        renderWindow();
    }

    public void initCarTypes() {
        // carModel fill
        carModel.getItems().clear();
        // add every CarModel to carModel MenuButton
        carModel.getItems().addAll(
                Stream.of(CarModel.values())
                        .map((e) -> {
                            MenuItem item = new MenuItem(e.name());
                            item.setOnAction((action) -> carModel.setText(item.getText()));
                            return item;
                        })
                        .collect(Collectors.toList()));
    }

    @FXML
    void generatePayment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_FXML_NAME));
            Parent root = (Parent) loader.load();
            ControllerViewPayment controllerPayment = loader.getController();
            controllerPayment.getControllerParking().getControllerVehicle().setVehiclelist(controllerParking.getControllerVehicle().getVehiclelist());
            System.out.println(controllerPayment.getControllerParking().getControllerVehicle().getVehiclelist());
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

    /**
     * Function to call when Car is selected
     */

    @FXML
    void carSelectActivate(ActionEvent event) {
        carModel.setDisable(false);
    }

    /**
     * Function to call when Motorcycle is selected
     */
    @FXML
    void motorcycleSelectActivate(ActionEvent event) {
        carModel.setDisable(true);
        carModel.setText("Tipo de Vehículo");
    }

    /**
     * Function to call when 'insertCar' button is pressed
     */
    @FXML
    void modifyVehicle(ActionEvent event) throws InvalidLicensePlate {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(MODIFY_FXML_NAME));
            Parent root = (Parent) loader.load();
            ControllerModifyVehicle controllerModify = loader.getController();
            controllerModify.getControllerParking().getControllerVehicle().setVehiclelist(controllerParking.getControllerVehicle().getVehiclelist());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET_NAME).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ICON_NAME))));
            stage.setTitle("Realizar Modificacion");
            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void insertCar(ActionEvent event) {
        // Obtener el tipo de vehículo
        Vehicle new_vehicle;
        // Crear el vehículo
        // NOTA: Los vehículos siempre se guardan con placas en MAYÚSCULAS
        try {
            if (selectedCar.isSelected()) {
                new_vehicle = new Car(textLicensePlate.getText(), Calendar.getInstance(), CarModel.valueOf(carModel.getText()));
            } else if (selectedMotorcycle.isSelected()) {
                new_vehicle = new MotorCycle(textLicensePlate.getText(), Calendar.getInstance());
            } else {
                // Ninguno fue seleccionado
                AlertUtils.alertMiniError("Seleccionar un tipo", "No seleccionaste un tipo de vehículo");
                return;
            }

        } catch (InvalidLicensePlate invalidPlate) {
            AlertUtils.alertError("Error vehículo", "La placa " + invalidPlate.getMessage() + " no es válida", "Por favor revisa que esté bien escrita");
            return;
        } catch (IllegalArgumentException invalidVehicleType) {
            AlertUtils.alertError("Error vehículo", "No ha seleccionado ningún tipo de vehículo", "Por favor seleccione un tipo de vehículo");
            return;
        }

        // Añadir el vehículo
        try {
            if (!controllerParking.getControllerVehicle().addVehicle(new_vehicle)) {
                AlertUtils.alertError("Error de inserción", "El vehículo ya está registrado", "Por favor revisa la placa");
                return;
            }
        } catch (ParkingFull e) {
            AlertUtils.alertError("Error al insertar vehículo", "El parqueadero se encuentra lleno", "No se insertó el vehículo");
            return;
        }

        // Mostrar una confirmación
        AlertUtils.alertInformation("Puesto de parqueo", "El vehículo de placas: " + new_vehicle.getLicensePlate() + " debe parquearse en el espacio: " + new_vehicle.getParkingPlace().toString(), "Vehículo registrado en el sistema con éxito");

        // Borrar el campo de datos
        textLicensePlate.clear();
        renderWindow();
    }
    @FXML
    void reload(ActionEvent event) {
        renderWindow();
    }
    public void renderWindow (){
        clearWindow();
        tableVehicle.getItems().addAll(controllerParking.getControllerVehicle().getVehiclelist().values());
        if (controllerParking.getControllerVehicle().getVehiclelist().size() > 0){
            btnGeneratePayment.setDisable(false);
            btnModify.setDisable(false);
        }
    }
    public void clearWindow (){
        tableVehicle.getItems().clear();
    }
    public ControllerParking getControllerParking() {
        return controllerParking;
    }

}
