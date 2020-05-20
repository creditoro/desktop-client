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
    private static final Map<Views, String> FXML_MAP = Map.of(
            Views.FRONTPAGE, "frontpage/Frontpage.fxml",
            Views.LOGIN, "login/Login.fxml",
            Views.BROWSE_CHANNELS, "browse_channels/BrowseChannels.fxml",
            Views.BROWSE_PRODUCTIONS, "browse_productions/BrowseProductions.fxml",
            Views.PRODUCTION, "production/Production.fxml",
            Views.ADD_CREDITS, "add_credits/AddCredits.fxml",
            Views.CHANNEL_PROGRAMS, "channel_programs/ChannelPrograms.fxml");
    private final ViewModelFactory viewModelFactory;
    private final Map<Views, Scene> sceneMap = new HashMap<>();
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
        openView(Views.FRONTPAGE);
        root.show();
    }

    /**
     * Open view.
     *
     * @param viewToOpen the view to open
     * @return the boolean if False.
     */
    public boolean openView(Views viewToOpen) {
        FXMLLoader loader = new FXMLLoader();
        Scene scene;
        try {
            scene = getScene(viewToOpen, loader);
        } catch (IOException e) {
            LOGGER.info(String.format("Couldn't find FXML file with name: %s", viewToOpen));
            return false;
        }
        root.setTitle("Creditoro");
        root.setScene(scene);
        return true;
    }

    public boolean openView(Views viewToOpen, String param) {
        FXMLLoader loader = new FXMLLoader();
        Scene scene;
        try {
            scene = getScene(viewToOpen, loader);
        } catch (IOException e) {
            LOGGER.info(String.format("Coudn't find FXML file with name: %s", viewToOpen));
            return false;
        }
        if (viewToOpen == Views.BROWSE_CHANNELS) {
            viewModelFactory.getBrowseChannelsViewModel().queryParamProperty().setValue(param);
            viewModelFactory.getBrowseChannelsViewModel().search();
        } else{
            viewModelFactory.getBrowseProductionsViewModel().queryParamProperty().setValue(param);
            viewModelFactory.getBrowseProductionsViewModel().search();
        }
        root.setTitle("Creditoro");
        root.setScene(scene);

        return true;
    }

    private Scene getScene(Views viewToOpen, FXMLLoader loader) throws IOException {
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
