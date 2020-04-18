package dk.creditoro.client.core;

import dk.creditoro.client.view.IViewController;
import dk.creditoro.client.view.login_page.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type View handler creates the views, loads the Fxml files and initializes the View Controllers.
 */
public class ViewHandler {

    private ViewModelFactory mvViewModel;

    private static final Map<String, String> FXML_MAP = Map.of("LoginPage", "login_page/LoginPage.fxml");
    private Map<String, Scene> SCENE_MAP = new HashMap<>();
    private Stage root;

    /**
     * Instantiates a new View handler.
     *
     * @param mvViewModel the mv view model
     */
    public ViewHandler(ViewModelFactory mvViewModel) {
        this.mvViewModel = mvViewModel;
        root = new Stage();
    }

    /**
     * Start.
     *
     * @throws IOException the io exception
     */
    public void start() throws IOException {
        openView("LoginPage");
        root.show();
    }

    /**
     * Open view.
     *
     * @param viewToOpen the view to open
     * @throws IOException the io exception
     */
    public void openView(String viewToOpen) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Scene scene = getScene(viewToOpen, loader);

        root.setTitle(viewToOpen);
        root.setScene(scene);
    }

    private Scene getScene(String viewToOpen, FXMLLoader loader) throws IOException {
        // check if we scene is present in SCENE_MAP
        Scene scene = SCENE_MAP.get(viewToOpen);
        if (scene != null) {
            return scene;
        }
        var fxmlPath = FXML_MAP.get(viewToOpen);
        if (fxmlPath == null) {
            throw new IOException(String.format("View (%s) not found", viewToOpen));
        }

        loader.setLocation(getClass().getResource(String.format("../view/%s", fxmlPath)));
        Parent parent = loader.load();
        scene = new Scene(parent);
        IViewController controller = loader.getController();
        controller.init(mvViewModel, this);
        SCENE_MAP.put(viewToOpen, scene);
        return scene;
    }

}