package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.devscite.Utils.AlertUtils;
import org.devscite.Utils.Exeptions.ViewException;
import org.devscite.Utils.ViewType;

import java.util.Optional;

public abstract class RealTimeObservableView {

    ViewManagementObserver observer = null;

    public void subscribe(ViewManagementObserver observer, ViewType type) throws ViewException {
        this.observer = observer;
        this.observer.subscribe(this, type);
    }

    public void unsubscribe() {
        this.observer.unsuscribe(this);
        this.observer = null;
    }

    /**
     * Notify the manager that an update was made
     */
    public void notifyUpdate() {
        this.observer.notifyUpdate();
    }

    /**
     * What the class should do when an update request is recieved
     */
    public abstract void onUpdate();

    /**
     * Initial controller state
     */
    public abstract void initState();

    /**
     * What to do on exit
     */
    public abstract void onExit();

    /**
     * Close current window
     *
     * @param event FXML event
     */
    @FXML
    public void close(ActionEvent event) {
        this.observer.unsuscribe(this);
        this.onExit();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void logOut(ActionEvent event) throws Exception {
        Optional<ButtonType> option = AlertUtils.alertConfirmation("Cerrar Sesion", "¿Esta seguro de que quiere cerrar la Sesion?", "");
        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            this.observer.createView(ControllerViewUserRealTime.MAIN_FXML_NAME, ControllerViewUserRealTime.WINDOW_NAME, ControllerViewUserRealTime.ICON_NAME, new Stage(), ViewType.SLAVE_UNIQUE);
            close(event);
        }
    }
}
