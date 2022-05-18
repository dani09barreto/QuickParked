package org.devscite.Interface.View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class RealTimeUpdateView<T> {

    ViewManagementController<T> manager = null;

    /**
     * Attach manager to the class
     *
     * @param manager Manager to attach
     */
    public void attachManager(ViewManagementController<T> manager) {
        this.manager = manager;
    }

    /**
     * Notify the manager that an update was made
     */
    public void notifyUpdate() {
        this.manager.notifyUpdate();
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
        this.manager.optOut(this);
        this.onExit();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
