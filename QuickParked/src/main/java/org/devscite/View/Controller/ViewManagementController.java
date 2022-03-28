package org.devscite.View.Controller;

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
 * Class that manages all views, is the owner of the data and lends it to the view (RealTimeUpdateView)
 *
 * @param <T>
 */
public class ViewManagementController<T> {

    private final T data;

    private final HashMap<RealTimeUpdateView<T>, ViewType> associatedViews = new HashMap<>();

    /**
     * Create a manager, with specified data
     *
     * @param data Data to hold, and lend to views
     */
    public ViewManagementController(T data) {
        this.data = data;
    }

    /**
     * When a associated RealTimeUpdateView updates the controller
     * Notify and Update every associated RealTimeUpdateView
     */
    public void notifyUpdate() {
        associatedViews.keySet().forEach(RealTimeUpdateView::onUpdate);
    }

    /**
     * Deattach a view from the manager (View is closed)
     *
     * @param view View to remove
     */
    public void optOut(RealTimeUpdateView<T> view) {

        // Check if this is a master view
        if (this.associatedViews.get(view) == ViewType.MASTER) {
            Platform.exit();
        }

        this.associatedViews.remove(view);
    }

    /**
     * Attach a view to the manager
     *
     * @param view View to attach
     * @param type Type of view
     * @throws ViewException View cannot be created because of view type
     */
    public void optIn(RealTimeUpdateView<T> view, ViewType type) throws ViewException {
        // Check type
        if (type == ViewType.MASTER && this.associatedViews.containsValue(ViewType.MASTER))
            throw new ViewException("Master view already exists");
        else if (type == ViewType.SLAVE_UNIQUE) {
            for (RealTimeUpdateView<T> v : this.associatedViews.keySet()) {
                if (v.getClass() == view.getClass()) throw new ViewException("Slave view already exists");
            }
        }

        this.associatedViews.put(view, type);
    }

    /**
     * Get inner data
     *
     * @return Data type
     */
    public T getData() {
        return data;
    }

    /**
     * Create a new View
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

        RealTimeUpdateView<T> viewController = Objects.requireNonNull(loader.getController());
        viewController.attachManager(this);
        this.optIn(Objects.requireNonNull(viewController), type);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../styles.css")).toExternalForm());

        stage.setOnCloseRequest(windowEvent -> {
            optOut(viewController);
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
