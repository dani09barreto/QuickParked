package org.devscite.Interface.View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.devscite.Interface.View.Controller.*;
import org.devscite.Utils.ViewType;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new ViewManagementObserver().createView(
                ControllerViewUserRealTime.MAIN_FXML_NAME,
                ControllerViewUserRealTime.WINDOW_NAME,
                ControllerViewUserRealTime.ICON_NAME, stage, ViewType.SLAVE);
    }

    public static void main(String[] args) {
        launch();
    }
}