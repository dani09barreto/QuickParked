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

/**
 * Makes a class Observable to ViewManagementObserver, implements usefull javafx Windowing methods and
 * requires an update behaviour overriding with 'onUpdate'
 */
public abstract class RealTimeObservableView {

    ViewManagementObserver observer = null;

    /**
     * Subscribe to an Observer
     *
     * @param observer Observer to susbcribe to
     * @param type     Type of view
     * @throws ViewException View cannot be subscribed because another MASTER or SLAVE_UNIQUE are already subscribed
     */
    public void subscribe(ViewManagementObserver observer, ViewType type) throws ViewException {
        this.observer = observer;
        this.observer.subscribe(this, type);
    }

    /**
     * Unsubscribe this view from the Observer
     */
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

    /**
     * Close the current session
     *
     * @param event FXML event
     * @throws Exception Observer internal error
     */
    @FXML
    void logOut(ActionEvent event) throws Exception {
        Optional<ButtonType> option = AlertUtils.alertConfirmation("Cerrar Sesion", "¿Esta seguro de que quiere cerrar la Sesion?", "");
        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            this.observer.createView(ControllerViewUserRealTime.MAIN_FXML_NAME, ControllerViewUserRealTime.WINDOW_NAME, ControllerViewUserRealTime.ICON_NAME, new Stage(), ViewType.SLAVE_UNIQUE);
            close(event);
        }
    }
}
