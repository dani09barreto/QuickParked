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

    void resetLabels() {
        this.passwordField.setText("");
        this.userField.setText("");
    }
    @FXML
    void logIn(ActionEvent event) {
        String username = userField.getText();
        String password = passwordField.getText();
        UserParking userlogIn = null;
        try {
            userlogIn = manager.getController().getControllerUser().getiUserDAO().searchUser(username, password);
            if (userlogIn instanceof Employee){
                this.manager.createView(
                        ControllerViewParking.MAIN_FXML_NAME,
                        ControllerViewParking.WINDOW_NAME,
                        ControllerViewParking.ICON_NAME, new Stage(), ViewType.SLAVE_UNIQUE);
                close(event);
            }
            else if (userlogIn instanceof Admin){
                this.manager.createView(
                        ControllerViewOwnerParking.MAIN_FXML_NAME,
                        ControllerViewOwnerParking.WINDOW_NAME,
                        ControllerViewOwnerParking.ICON_NAME, new Stage(), ViewType.SLAVE_UNIQUE);
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
