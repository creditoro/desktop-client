package dk.creditoro.client.view.login;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
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

    private LoginViewModel viewModel;
    private ViewHandler viewHandler;


    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        viewModel = viewModelFactory.getLoginViewModel();
        this.viewHandler = viewHandler;

        txtEmail.textProperty().bindBidirectional(viewModel.emailProperty());
        txtPassword.textProperty().bindBidirectional(viewModel.passwordProperty());
        lblResult.textProperty().bindBidirectional(viewModel.loginResultProperty());
        lblResult.textProperty().addListener(this::onLoginResult);
    }

    private void onLoginResult(Observable observable, String old, String newVal) {
        if (newVal.equals("OK")) {
            LOGGER.info("Logged in");
            viewHandler.openView("LoginPage");
        }
    }

    public void onLoginButton() {
        viewModel.validateLogin();
    }
}