package dk.creditoro.client.core;

import dk.creditoro.client.view.login_page.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

/**
 * The type View handler creates the views, loads the Fxml files and initializes the View Controllers.
 */
public class ViewHandler {

    private ViewModelFactory mvViewModel;

    private static final Map<String, String> FXML_MAP = Map.of("LoginPage", "login_page/LoginPage.fxml");

    /**
     * Instantiates a new View handler.
     *
     * @param mvViewModel the mv view model
     */
    public ViewHandler(ViewModelFactory mvViewModel) {
        this.mvViewModel = mvViewModel;
    }

    /**
     * Start.
     *
     * @throws IOException the io exception
     */
    public void start() throws IOException {
        openView("LoginPage");
    }

    /**
     * Open view.
     *
     * @param viewToOpen the view to open
     * @throws IOException the io exception
     */
    public void openView(String viewToOpen) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage tmpStage = new Stage();
        var fxmlPath = FXML_MAP.get(viewToOpen);
        if (fxmlPath == null) {
            throw new IOException(String.format("View (%s) not found", viewToOpen));
        }
        loader.setLocation(getClass().getResource(String.format("../view/%s", fxmlPath)));
        Parent root = loader.load();

        switch (viewToOpen) {
            case "LoginPage":
                LoginController controller = loader.getController();
                controller.init(mvViewModel.getLoginViewModel());
                break;
            default:
                // TODO handle default case.
        }

        tmpStage.setTitle(viewToOpen);
        Scene scene = new Scene(root);
        tmpStage.setScene(scene);
        tmpStage.show();
    }
}