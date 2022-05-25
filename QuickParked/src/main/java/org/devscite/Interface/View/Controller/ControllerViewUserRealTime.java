package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.devscite.Entities.Model.Admin;
import org.devscite.Entities.Model.Employee;
import org.devscite.Entities.Model.UserParking;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.Exeptions.InvalidUser;
import org.devscite.Utils.ViewType;
import org.devscite.structure.Controller.ControllerParking;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerViewUserRealTime extends RealTimeObservableView implements Initializable {
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

    void resetLabels() {
        this.passwordField.setText("");
        this.userField.setText("");
    }

    @FXML
    void logIn(ActionEvent event) {
        // Obtain the login credentials
        String username = userField.getText();
        String password = passwordField.getText();
        UserParking userlogIn;

        try {
            userlogIn = ControllerParking.getInstance().getControllerUser().getiUserDAO().searchUser(username, password);
            if (userlogIn instanceof Employee) {
                this.observer.createView(
                        ControllerViewParkingRealTime.MAIN_FXML_NAME,
                        ControllerViewParkingRealTime.WINDOW_NAME,
                        ControllerViewParkingRealTime.ICON_NAME, new Stage(), ViewType.SLAVE_UNIQUE);
                close(event);
            } else if (userlogIn instanceof Admin) {
                this.observer.createView(
                        ControllerViewOwnerParkingRealTime.MAIN_FXML_NAME,
                        ControllerViewOwnerParkingRealTime.WINDOW_NAME,
                        ControllerViewOwnerParkingRealTime.ICON_NAME, new Stage(), ViewType.SLAVE_UNIQUE);
                close(event);
            }
        } catch (InvalidUser e) {
            AlertUtils.alertError("Error", "No se pudo iniciar sesión contraseña o usuarios invalidos", "");
            resetLabels();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
