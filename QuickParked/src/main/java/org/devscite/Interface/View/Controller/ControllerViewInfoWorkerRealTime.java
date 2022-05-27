package org.devscite.Interface.View.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.devscite.structure.Controller.ControllerParking;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ControllerViewInfoWorkerRealTime extends RealTimeObservableView implements Initializable {
    public final static String MAIN_FXML_NAME = "filesFXML/infoWorkerScene.fxml";
    public final static String WINDOW_NAME = "Informacion Trabajador";
    public final static String ICON_NAME = "img/logo_mini.png";

    @FXML
    private Button backBtn;

    @FXML
    private Label documentWorkerLabel;

    @FXML
    private Label nameWorkerLabel;

    @FXML
    private Label phoneWorkerLabel;

    @FXML
    private Label timeSesionLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        nameWorkerLabel.setText(ControllerParking.getInstance().getActualUser().getName());
        phoneWorkerLabel.setText(String.valueOf(ControllerParking.getInstance().getActualUser().getNumber()));
        documentWorkerLabel.setText(String.valueOf(ControllerParking.getInstance().getActualUser().getDocument()));
        timeSesionLabel.setText(time.format(ControllerParking.getInstance().getTimeSesion().getTime()));
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
