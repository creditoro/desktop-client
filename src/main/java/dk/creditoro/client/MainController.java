package dk.creditoro.client;

import dk.creditoro.exceptions.HttpStatusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * The type Main controller.
 */
public class MainController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * The Txt username.
     */
    @FXML
    public TextField txtUsername;
    /**
     * The Txt password.
     */
    @FXML
    public TextField txtPassword;
    /**
     * The Btn sign in.
     */
    @FXML
    public Button btnSignIn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO Setup code here
    }

    /**
     * Sign in.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void signIn(ActionEvent actionEvent) {
        String email = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            App.login.signIn(email, password);
        } catch (IOException | HttpStatusException e) {
            LOGGER.info(String.format("error: %s", e));
        }

        //Change scene
        try {
            App.setRoot("SearchChannels");
        } catch (IOException e) {
            LOGGER.info(String.format("error: %s", e));
        }
    }
}
