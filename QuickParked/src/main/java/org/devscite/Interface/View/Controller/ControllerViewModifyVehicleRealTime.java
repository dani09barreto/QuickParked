package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.devscite.structure.Controller.ControllerParking;
import org.devscite.Entities.Model.Car;
import org.devscite.Entities.Enums.CarModel;
import org.devscite.Entities.Model.Vehicle;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.Exeptions.InvalidLicensePlate;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerViewModifyVehicleRealTime extends RealTimeObservableView implements Initializable {

    public final static String MAIN_FXML_NAME = "filesFXML/ModifyScene.fxml";
    public final static String WINDOW_NAME = "Modificar vehículo";
    public final static String ICON_NAME = "img/logo_mini.png";

    @FXML
    public Button backBtn;

    @FXML
    public Button modifyBtn;

    @FXML
    private TextField plateTextField;

    @FXML
    private ListView<String> platesList;

    @FXML
    private ComboBox<CarModel> vehicleType;

    void updatePlateList() {
        platesList.getItems().clear();
        platesList.getItems().setAll(ControllerParking.getInstance().getControllerVehicle().getVehicles().keySet());
    }

    @Override
    public void onUpdate() {
        updatePlateList();
    }

    @Override
    public void initState() {
        onUpdate();
    }

    @Override
    public void onExit() {

    }

    @FXML
    public void plateSelect() {
        String plate = platesList.getSelectionModel().getSelectedItem();
        if (plate != null) {
            // Show plate info into fields
            Vehicle vehicle = ControllerParking.getInstance().getControllerVehicle().getVehicle(plate);
            plateTextField.setText(plate);

            // Set Car Model
            if (vehicle instanceof Car) {
                vehicleType.setDisable(false);
                vehicleType.setValue(((Car) vehicle).getCarModel());
            } else {
                vehicleType.setDisable(true);
            }
        }
    }

    @FXML
    void makeModify(ActionEvent event) {
        try {
            String placaModificar = platesList.getSelectionModel().getSelectedItem();
            Vehicle vehicle = ControllerParking.getInstance().getControllerVehicle().getVehicle(placaModificar);

            String nuevaPlaca = plateTextField.getText();
            vehicle.setLicensePlate(nuevaPlaca);

            if (!vehicleType.isDisabled() && vehicle instanceof Car) {
                ((Car) vehicle).setCarModel(vehicleType.getValue());
            }

            this.notifyUpdate();

            // Success
            AlertUtils.alertInformation("Modificacion hecha!", "Se ha realizado de forma exitosa la modificacion.", "");

            close(event);

        } catch (NullPointerException e) {
            AlertUtils.alertError("Error en modificación", "No seleccionaste ningún vehículo.", "Por favor selecciona un vehículo");
        } catch (InvalidLicensePlate e) {
            AlertUtils.alertError("Error en modificación", "Placa inválida", "Digita una placa valida");
        } catch (Exception e) {
            AlertUtils.alertError("Error en modificación", "El vehiculo no se ha podido modificar.", "Vuelve a intentarlo");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicleType.getItems().setAll(CarModel.values());
    }
}
