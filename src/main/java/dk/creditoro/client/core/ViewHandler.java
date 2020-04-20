package dk.creditoro.client.core;

import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The type View handler creates the views, loads the Fxml files and initializes the View Controllers.
 */
public class ViewHandler {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final Map<String, String> FXML_MAP = Map.of(
            "Login", "login/Login.fxml",
            "BrowseChannels", "browse_channels/BrowseChannels.fxml");
    private final ViewModelFactory viewModelFactory;
    private final Map<String, Scene> sceneMap = new HashMap<>();
    private final Stage root;

    /**
     * Instantiates a new View handler.
     *
     * @param viewModelFactory the mv view model
     */
    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        root = new Stage();
    }

    /**
     * Start.
     */
    public void start() {
        openView("Login");
        root.show();
    }

    /**
     * Open view.
     *
     * @param viewToOpen the view to open
     * @return the boolean if False.
     */
    public boolean openView(String viewToOpen) {
        FXMLLoader loader = new FXMLLoader();
        Scene scene;
        try {
            scene = getScene(viewToOpen, loader);
        } catch (IOException e) {
            LOGGER.info(String.format("Coudn't find FXML file with name: %s", viewToOpen));
            return false;
        }
        root.setTitle(viewToOpen);
        root.setScene(scene);
        return true;
    }

    private Scene getScene(String viewToOpen, FXMLLoader loader) throws IOException {
        // check if we scene is present in sceneMap
        Scene scene = sceneMap.get(viewToOpen);
        if (scene != null) {
            return scene;
        }

        var fxmlPath = FXML_MAP.get(viewToOpen);
        if (fxmlPath == null) {
            throw new IOException(String.format("View (%s) not found", viewToOpen));
        }
        loader.setLocation(getClass().getResource(String.format("/dk/creditoro/client/view/%s", fxmlPath)));
        Parent parent = loader.load();
        IViewController controller = loader.getController();
        controller.init(viewModelFactory, this);
        scene = new Scene(parent);
        sceneMap.put(viewToOpen, scene);
        return scene;
    }

}