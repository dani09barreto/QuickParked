package org.devscite.Interface.View.Controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.devscite.Utils.Exeptions.ViewException;
import org.devscite.Utils.ViewType;

import java.util.HashMap;
import java.util.Objects;

/**
 * Class that manages all real time viws
 */
public class ViewManagementObserver {

    private final HashMap<RealTimeObservableView, ViewType> associatedViews = new HashMap<>();

    /**
     * When a associated RealTimeUpdateView updates the controller
     * Notify and Update every associated RealTimeUpdateView
     */
    public void notifyUpdate() {
        associatedViews.keySet().forEach(RealTimeObservableView::onUpdate);
    }

    /**
     * Deattach a view from the manager (View is closed)
     *
     * @param view View to remove
     */
    public void unsuscribe(RealTimeObservableView view) {

        // Check if this is a master view
        if (this.associatedViews.get(view) == ViewType.MASTER) {
            Platform.exit();
        }

        this.associatedViews.remove(view);
    }

    /**
     * Subscribe a View to the Observer
     *
     * @param view View to subscribe, Must be ObservableView
     * @param type ViewType MASTER or SLAVE
     * @throws ViewException a MASTER or same type UNIQUE_SLAVE already existed
     */
    public void subscribe(RealTimeObservableView view, ViewType type) throws ViewException {
        // Check type
        if (type == ViewType.MASTER && this.associatedViews.containsValue(ViewType.MASTER))
            throw new ViewException("Master view already exists");
        else if (type == ViewType.SLAVE_UNIQUE) {
            for (RealTimeObservableView v : this.associatedViews.keySet()) {
                if (v.getClass().getName().equals(view.getClass().getName()))
                    throw new ViewException("Slave view already exists");
            }
        }

        this.associatedViews.put(view, type);
    }

    /**
     * Create a new View and automatically subscribe it
     *
     * @param fxml  FXML file path
     * @param title Window title
     * @param icon  Window Icon path
     * @param stage Stage, usually new Stage
     * @param type  Type of View, only one MASTER can exist, only one SLAVE_UNIQUE can exist for every different view, many SLAVE can exist
     * @throws Exception FXML exception or ViewException
     */
    public void createView(String fxml, String title, String icon, Stage stage, ViewType type) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        RealTimeObservableView viewController = Objects.requireNonNull(loader.getController());
        viewController.subscribe(this, type);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../styles.css")).toExternalForm());

        stage.setOnCloseRequest(windowEvent -> {
            unsuscribe(viewController);
            viewController.onExit();
        });

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(icon))));
        stage.setTitle(title);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();

        viewController.initState();
    }
}
