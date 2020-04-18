package dk.creditoro.client.view.login_page;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * The type Login controller.
 */
public class LoginController implements IViewController {
    /**
     * The Txt email.
     */
    @FXML
    public TextField txtEmail;
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

    private LoginViewModel viewModel;
    private ViewHandler viewHandler;

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * Init.
     *
     * @param vm the vm
     */
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        viewModel = viewModelFactory.getLoginViewModel();
        this.viewHandler = viewHandler;

        txtEmail.textProperty().bindBidirectional(viewModel.emailProperty());
        txtPassword.textProperty().bindBidirectional(viewModel.passwordProperty());

    }

    /**
     * On sign in button.
     */
    public void signIn() {
        viewModel.signIn(txtEmail.getText(), txtPassword.getText());
        try {
            viewHandler.openView("Channels");
        } catch (IOException e) {
            LOGGER.info("Couldn't find channels view.");
        }
    }
}