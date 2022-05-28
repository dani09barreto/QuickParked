package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.devscite.Aplication.aggregates.InformationDAOImpl;
import org.devscite.Entities.Enums.SlotType;
import org.devscite.Entities.Model.Employee;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.ViewType;
import org.devscite.structure.Controller.ControllerParking;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerViewOwnerParkingRealTime extends RealTimeObservableView implements Initializable {

    public final static String MAIN_FXML_NAME = "filesFXML/OwnerParkingScene.fxml";
    public final static String WINDOW_NAME = "Información Parqueadero";
    public final static String ICON_NAME = "img/logo_mini.png";
    private Map<SlotType, Integer> slots = new HashMap<>();
    private Integer carFee;
    private Integer motorcycleFee;
    private String parkingName;
    private String parkingAddress;
    public TextField userNameLabel;

    private final InformationDAOImpl database = new InformationDAOImpl();

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
    private TextField comunesSlotsLabel;

    @FXML
    private TextField documentWorkerLabel;

    @FXML
    private TextField electricosSlotsLabel;

    @FXML
    private TextField fireCareLabel;

    @FXML
    private TextField fireCycleLabel;

    @FXML
    private TextField grandesSlotsLabel;

    @FXML
    private Button listWorkers;

    @FXML
    private TextField motosSlotsLabel;

    @FXML
    private TextField nameParkingLabel;

    @FXML
    private TextField nameWorkerLabel;

    @FXML
    private PasswordField passWorkerLabel;

    @FXML
    void addInfoParking(ActionEvent event) {
        // Update slots
        int comunes = Integer.parseInt(this.comunesSlotsLabel.getText());
        int electricos = Integer.parseInt(this.electricosSlotsLabel.getText());
        int motos = Integer.parseInt(this.motosSlotsLabel.getText());
        int grandes = Integer.parseInt(this.grandesSlotsLabel.getText());

        if (comunes < 0 || electricos < 0 || motos < 0 || grandes < 0) {
            AlertUtils.alertError("Error", "Los datos son erróneos", "Por favor verifícalos");
            return;
        }

        try {
            // update slots
            database.setSlot(SlotType.STANDARD, comunes);
            database.setSlot(SlotType.ELECTRIC, electricos);
            database.setSlot(SlotType.MOTORCYCLE, motos);
            database.setSlot(SlotType.LARGE, grandes);

            // update name and address
            database.setParkingName(nameParkingLabel.getText());
            database.setParkingAddress(addressParkingLabel.getText());

            // update fees
            database.setParkingCarFee(Integer.parseInt(fireCareLabel.getText()));
            database.setParkingMotorcycleFee(Integer.parseInt(fireCycleLabel.getText()));

        } catch (SQLException e) {
            System.err.println("Error de conectividad en la base de datos");
            e.printStackTrace();
            AlertUtils.alertError("Base de datos", "Error de conectividad", "La base de datos no pudo establecer la conexión");
            return;
        } catch (Exception e) {
            AlertUtils.alertError("Error", "Los datos son erróneos", "Por favor verifícalos");
            return;
        }


        // confirmation
        AlertUtils.alertInformation(
                "Procedimiento exitoso",
                "Los datos han sido actualizados correctamente!",
                "Los datos fueron guardados en la base de datos");
    }

    @FXML
    void addWorker(ActionEvent event) {
        try {
            String name = nameWorkerLabel.getText();
            BigDecimal document = BigDecimal.valueOf(Long.parseLong(documentWorkerLabel.getText()));
            BigDecimal number = BigDecimal.valueOf(Long.parseLong(celWorkerLabel.getText()));
            String userN = userNameLabel.getText();
            String pass = passWorkerLabel.getText();
            Employee newEmp = new Employee(userN, pass, name, document, number);
            ControllerParking.getInstance().getControllerUser().getiUserDAO().addEmployee(newEmp);
            AlertUtils.alertConfirmation("Trabajador Agregado", "El emleado se ha agregado correctamente", "");
            clearWorker();
        } catch (Exception e) {
            AlertUtils.alertError("Error", "No se logro agregar el empleado", "intentelo de nuevo");
            e.printStackTrace();
        }
    }

    @FXML
    void listWorkers(ActionEvent event) throws Exception {
        this.observer.createView(
                ControllerViewWorkersRealTime.MAIN_FXML_NAME,
                ControllerViewWorkersRealTime.WINDOW_NAME,
                ControllerViewWorkersRealTime.ICON_NAME,
                new Stage(), ViewType.SLAVE_UNIQUE
        );
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void initState() {
        // Iniciar los contadores de Parqueaderos disponibles
        this.comunesSlotsLabel.setText(
                this.slots.get(SlotType.STANDARD).toString()
        );
        this.electricosSlotsLabel.setText(
                this.slots.get(SlotType.ELECTRIC).toString()
        );
        this.motosSlotsLabel.setText(
                this.slots.get(SlotType.MOTORCYCLE).toString()
        );
        this.grandesSlotsLabel.setText(
                this.slots.get(SlotType.LARGE).toString()
        );
        // Iniciar las tarifas
        this.fireCareLabel.setText(
                Integer.toString(carFee)
        );
        this.fireCycleLabel.setText(
                Integer.toString(motorcycleFee)
        );
        // Iniciar los datos básicos del parqueadero
        this.nameParkingLabel.setText(
                parkingName
        );
        this.addressParkingLabel.setText(
                parkingAddress
        );
    }

    @Override
    public void onExit() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            slots = this.database.getDefinedSlots();
            carFee = this.database.getParkingCarFee();
            motorcycleFee = this.database.getParkingMotorcycleFee();
            parkingName = this.database.getParkingName();
            parkingAddress = this.database.getParkingAddress();
        } catch (Exception e) {
            AlertUtils.alertError("Error", "Ha ocurrido un error en el sistema", e.getMessage());
            close(new ActionEvent());
        }
    }

    public void clearWorker (){
        userNameLabel.setText("");
        passWorkerLabel.setText("");
        nameWorkerLabel.setText("");
        celWorkerLabel.setText("");
        documentWorkerLabel.setText("");
    }
}

