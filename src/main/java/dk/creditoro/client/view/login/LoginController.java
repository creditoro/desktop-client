package dk.creditoro.client.view.login;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    /**
     * Lbl start menu pressed.
     *
     * @param mouseEvent the mouse event
     */
    public void lblStartMenuPressed(MouseEvent mouseEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        loginViewModel = viewModelFactory.getLoginViewModel();
        this.viewHandler = viewHandler;

        txtEmail.textProperty().bindBidirectional(loginViewModel.emailProperty());
        txtPassword.textProperty().bindBidirectional(loginViewModel.passwordProperty());

        loginViewModel.loginResponseProperty().addListener((observableValue, oldValue, newValue) -> onLoginResult(newValue));
    }

    private void onLoginResult(String response) {
        LOGGER.info(response);
        if (response.equals("OK")) {
            LOGGER.info("Logged in, switching view");
            loginViewModel.clearFields();
            viewHandler.openView(Views.BROWSE_CHANNELS);

        } else {
            createPopup("Incorrect Login", "Wrong credentials has been entered", 5, Pos.BASELINE_CENTER);
        }
    }

    /**
     * On login button.
     */
    public void onLoginButton() {
        loginViewModel.login();
    }

    public void createPopup(String title, String text, int duration, Pos position) {
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(duration))
                .position(position)
                .onAction(actionEvent -> LOGGER.info("Pressed popup"));
        notificationBuilder.show();
    }

    public void debugLogin() {
        txtEmail.setText("string@string.dk");
        txtPassword.setText("string");
        loginViewModel.login();
    }

    public void btnFrontPage(MouseEvent mouseEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    public void btnProductions(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    public void btnChannels(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    public void btnSearch(ActionEvent actionEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }
}