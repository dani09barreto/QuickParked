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

    final ViewManagementObserver viewManager = new ViewManagementObserver();

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
                        ControllerParking.getInstance().getControllerVehicle().addVehicle(new Car(randomPlate, Calendar.getInstance(), CarModel.values()[random.nextInt(4)]));
                    } catch (Exception ignored) {

                    }
                    break;
                case 1:
                    randomPlate = String.valueOf(new char[]{
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length())),
                            alphabet.charAt(random.nextInt(alphabet.length()))}) + random.nextInt(100) + alphabet.charAt(random.nextInt(alphabet.length()));
                    try {
                        ControllerParking.getInstance().getControllerVehicle().addVehicle(new MotorCycle(randomPlate, Calendar.getInstance()));
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
                ControllerViewUserRealTime.MAIN_FXML_NAME,
                ControllerViewUserRealTime.WINDOW_NAME,
                ControllerViewUserRealTime.ICON_NAME, stage, ViewType.SLAVE);
    }

    public static void main(String[] args) {
        launch();
    }
}