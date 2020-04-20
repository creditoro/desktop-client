package dk.creditoro.client.view.login;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.logging.Logger;

/**
 * The type Login controller.
 */
public class LoginController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label lblResult;

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        loginViewModel = viewModelFactory.getLoginViewModel();
        this.viewHandler = viewHandler;

        txtEmail.textProperty().bindBidirectional(loginViewModel.emailProperty());
        txtPassword.textProperty().bindBidirectional(loginViewModel.passwordProperty());

        lblResult.textProperty().bindBidirectional(loginViewModel.loginResponseProperty());
        loginViewModel.loginResponseProperty().addListener((observableValue, oldValue, newValue) -> onLoginResult(newValue));
    }

    private void onLoginResult(String response) {
        if (!response.isEmpty()) {
            LOGGER.info("Logged in, switching view");
            loginViewModel.clearFields();
            viewHandler.openView("BrowseChannels");
        }
    }

    /**
     * On login button.
     */
    public void onLoginButton() {
        loginViewModel.login();
    }
}