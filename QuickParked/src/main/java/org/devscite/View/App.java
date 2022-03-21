package org.devscite.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.devscite.Controller.ControllerParking;
import org.devscite.Model.Car;
import org.devscite.Model.CarModel;
import org.devscite.Model.Vehicle;

import java.util.Calendar;

public class App extends Application {
    private final static String ICON_NAME = "img/Logo3.png";
    private final static String MAIN_FXML_NAME = "ParkedScene.fxml";
    private final static String STYLE_SHEET_NAME = "styles.css";
    private final static String WINDOW_NAME = "QuickParked";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_FXML_NAME));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(STYLE_SHEET_NAME).toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_NAME)));
        stage.setTitle(WINDOW_NAME);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}