package org.devscite.View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Model.MotorCycle;
import org.devscite.Utils.Exeptions.InvalidLicensePlate;
import org.devscite.Utils.Exeptions.ParkingFull;
import org.devscite.Utils.ViewType;
import org.devscite.View.Controller.ControllerViewParking;
import org.devscite.View.Controller.ViewManagementController;

import java.util.Calendar;
import java.util.Random;

public class App extends Application {

    ViewManagementController<ControllerParking> viewManager = new ViewManagementController<>(new ControllerParking());

    private void generateDebugData() {
        //TODO: DEBUG
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomPlate;

        for (int i = 0; i < 50; i++) {
            switch (random.nextInt(2)) {
                case 0:
                    randomPlate = String.valueOf(new char[]{
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length()))}) + random.nextInt(1000);
                    try {
                        viewManager.getController().getControllerVehicle().addVehicle(new Car(randomPlate, Calendar.getInstance(), CarModel.Automovil));
                    } catch (Exception ignored) {

                    }
                    break;
                case 1:
                    randomPlate = String.valueOf(new char[]{
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length()))}) + random.nextInt(100) + alphabet.charAt(random.nextInt(alphabet.length()));
                    try {
                        viewManager.getController().getControllerVehicle().addVehicle(new MotorCycle(randomPlate, Calendar.getInstance()));
                    } catch (Exception ignored) {

                    }
                    break;
            }

        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        generateDebugData();

        viewManager.createView(
                ControllerViewParking.MAIN_FXML_NAME,
                ControllerViewParking.WINDOW_NAME,
                ControllerViewParking.ICON_NAME, stage, ViewType.MASTER);
    }

    public static void main(String[] args) {
        launch();
    }
}