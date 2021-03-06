package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.devscite.structure.Controller.ControllerParking;
import org.devscite.Entities.Model.Car;
import org.devscite.Entities.Enums.CarModel;
import org.devscite.Entities.Model.MotorCycle;
import org.devscite.Entities.Model.Vehicle;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.Exeptions.InvalidLicensePlate;
import org.devscite.Utils.Exeptions.ParkingFull;
import org.devscite.Utils.Exeptions.ViewException;
import org.devscite.Utils.ViewType;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerViewParkingRealTime extends RealTimeObservableView implements Initializable {

    public final static String MAIN_FXML_NAME = "filesFXML/ParkedScene.fxml";
    public final static String WINDOW_NAME = "QuickParked";
    public final static String ICON_NAME = "img/logo_mini.png";

    @FXML
    public Button addVehicleBtn;
    @FXML
    private Button infoWorkerbtn;

    @FXML
    private Button btnLogOut;

    @FXML
    public ToggleGroup type_vehicle;

    @FXML
    private Button generatePaymentBtn;

    @FXML
    private TextField licensePlateAddField;

    @FXML
    private Button modifyBtn;

    @FXML
    private RadioButton selectedCar;

    @FXML
    private RadioButton selectedMotorcycle;

    @FXML
    private MenuButton vehicleTypeList;

    @FXML
    private TableView<Vehicle> vehicleTable;

    @FXML
    public TableColumn<Vehicle, String> model;

    @FXML
    public TableColumn<Vehicle, String> plate;

    @FXML
    public TableColumn<Vehicle, String> inHour;

    @FXML
    public TableColumn<Vehicle, Integer> fee;

    @FXML
    public TableColumn<Vehicle, Integer> place;

    @FXML
    private Label nameWorker;

    @FXML
    void addVehicle(ActionEvent event) {
        Vehicle new_vehicle;
        // Crear el veh??culo
        // NOTA: Los veh??culos siempre se guardan con placas en MAY??SCULAS
        try {
            if (selectedCar.isSelected()) {
                new_vehicle = new Car(licensePlateAddField.getText(), Calendar.getInstance(), CarModel.valueOf(vehicleTypeList.getText()));
            } else if (selectedMotorcycle.isSelected()) {
                //TODO: Agregar tipos de motos en tal caso
                new_vehicle = new MotorCycle(licensePlateAddField.getText(), Calendar.getInstance());
            } else {
                // Ninguno fue seleccionado
                AlertUtils.alertMiniError("Seleccionar un tipo", "No seleccionaste un tipo de veh??culo");
                return;
            }
        } catch (InvalidLicensePlate invalidPlate) {
            AlertUtils.alertError("Error veh??culo", "La placa " + invalidPlate.getMessage() + " no es v??lida", "Por favor revisa que est?? bien escrita");
            return;
        } catch (IllegalArgumentException invalidVehicleType) {
            AlertUtils.alertError("Error veh??culo", "No ha seleccionado ning??n tipo de veh??culo", "Por favor seleccione un tipo de veh??culo");
            return;
        }

        // A??adir el veh??culo
        try {
            if (!ControllerParking.getInstance().getControllerVehicle().addVehicle(new_vehicle)) {
                AlertUtils.alertError("Error de inserci??n", "El veh??culo ya est?? registrado", "Por favor revisa la placa");
                return;
            }
        } catch (ParkingFull e) {
            AlertUtils.alertError("Error al insertar veh??culo", "El parqueadero se encuentra lleno", "No se insert?? el veh??culo");
            return;
        }

        // Borrar el campo de datos
        licensePlateAddField.clear();

        //! Notify UPDATE
        this.notifyUpdate();

        // Mostrar una confirmaci??n
        AlertUtils.alertInformation("Puesto de parqueo", "El veh??culo de placas: " + new_vehicle.getLicensePlate() + " debe parquearse en el espacio: " + new_vehicle.getParkingPlace().toString(), "Veh??culo registrado en el sistema con ??xito");
    }

    @FXML
    void generatePayment(ActionEvent event) {
        try {
            this.observer.createView(
                    ControllerViewPaymentRealTime.MAIN_FXML_NAME,
                    ControllerViewPaymentRealTime.WINDOW_NAME,
                    ControllerViewPaymentRealTime.ICON_NAME,
                    new Stage(), ViewType.SLAVE_UNIQUE);
        } catch (ViewException e) {
            AlertUtils.alertMiniWarning("Error", "Ya tienes una ventana de pago abierta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modifyVehicle(ActionEvent event) {
        try {
            this.observer.createView(
                    ControllerViewModifyVehicleRealTime.MAIN_FXML_NAME,
                    ControllerViewModifyVehicleRealTime.WINDOW_NAME,
                    ControllerViewModifyVehicleRealTime.ICON_NAME,
                    new Stage(), ViewType.SLAVE_UNIQUE);
        } catch (ViewException e) {
            AlertUtils.alertMiniWarning("Error", "Ya tienes una ventana de modificar abierta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectedType(ActionEvent event) {
        if (selectedCar.isSelected()) {
            vehicleTypesCar();
        } else if (selectedMotorcycle.isSelected()) {
            vehicleTypesMotorcycle();
        }
    }


    @FXML
    void showInfoWorker(ActionEvent event) throws Exception {
        this.observer.createView(

                ControllerViewInfoWorkerRealTime.MAIN_FXML_NAME,
                ControllerViewInfoWorkerRealTime.WINDOW_NAME,
                ControllerViewInfoWorkerRealTime.ICON_NAME,
                new Stage(), ViewType.SLAVE_UNIQUE
        );
    }

    public void updateVehicleList() {
        vehicleTable.getItems().clear();
        vehicleTable.getItems().addAll(ControllerParking.getInstance().getControllerVehicle().getVehicles().values());

        if (ControllerParking.getInstance().getControllerVehicle().getVehicles().size() > 0) {
            generatePaymentBtn.setDisable(false);
            modifyBtn.setDisable(false);
        } else {
            generatePaymentBtn.setDisable(true);
            modifyBtn.setDisable(true);
        }
    }

    public void vehicleTypesCar() {
        vehicleTypeList.getItems().clear();
        vehicleTypeList.setText("Tipo de carro");
        vehicleTypeList.getItems().addAll(Stream.of(CarModel.values()).map((e) -> {
            MenuItem item = new MenuItem(e.name());
            item.setOnAction((action) -> vehicleTypeList.setText(item.getText()));
            return item;
        }).collect(Collectors.toList()));
        vehicleTypeList.setDisable(false);
    }

    public void vehicleTypesMotorcycle() {
        vehicleTypeList.getItems().clear();
        vehicleTypeList.setText("Tipo de moto");
        vehicleTypeList.setDisable(true);
    }

    public void vehicleTypesDefault() {
        vehicleTypeList.getItems().clear();
        vehicleTypeList.setText("Tipo de veh??culo");
        vehicleTypeList.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Vehicle type init
        vehicleTypesDefault();
        nameWorker.setText(ControllerParking.getInstance().getActualUser().getName());
    }

    @Override
    public void onUpdate() {
        updateVehicleList();
    }

    @Override
    public void initState() {
        onUpdate();
    }

    @Override
    public void onExit() {

    }
}
