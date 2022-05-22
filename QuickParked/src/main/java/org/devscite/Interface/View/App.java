package org.devscite.Interface.View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.devscite.Interface.View.Controller.*;
import org.devscite.structure.Controller.ControllerParking;
import org.devscite.Entities.Model.Car;
import org.devscite.Entities.Enums.CarModel;
import org.devscite.Entities.Model.MotorCycle;
import org.devscite.Utils.ViewType;

import java.util.Calendar;
import java.util.Random;

public class App extends Application {

    final ViewManagementController<ControllerParking> viewManager = new ViewManagementController<>(new ControllerParking());

    private void generateDebugData() {
        //TODO: DEBUG
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomPlate;

        for (int i = 0; i < 50; i++) {
            switch (random.nextInt(3)) {
                case 2:
                case 0:
                    randomPlate = String.valueOf(new char[]{
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length()))}) + random.nextInt(1000);
                    try {
                        viewManager.getController().getControllerVehicle().addVehicle(new Car(randomPlate, Calendar.getInstance(), CarModel.values()[random.nextInt(4)]));
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
                ControllerViewUser.MAIN_FXML_NAME,
                ControllerViewUser.WINDOW_NAME,
                ControllerViewUser.ICON_NAME, stage, ViewType.SLAVE);
    }

    public static void main(String[] args) {
        launch();
    }
}