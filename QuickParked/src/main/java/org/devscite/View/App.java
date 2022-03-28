package org.devscite.View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Utils.ViewType;
import org.devscite.View.Controller.ControllerViewParking;
import org.devscite.View.Controller.ViewManagementController;

import java.util.Calendar;

public class App extends Application {

    ViewManagementController<ControllerParking> viewManager = new ViewManagementController<>(new ControllerParking());

    @Override
    public void start(Stage stage) throws Exception {

        //TODO: DEBUF
        viewManager.getData().getControllerVehicle().addVehicle(new Car("ABC123", Calendar.getInstance(), CarModel.Automovil));

        viewManager.createView(
                ControllerViewParking.MAIN_FXML_NAME,
                ControllerViewParking.WINDOW_NAME,
                ControllerViewParking.ICON_NAME, stage, ViewType.MASTER);
    }

    public static void main(String[] args) {
        launch();
    }
}