package dk.creditoro.client.view.login;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
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
    private ViewModelFactory viewModelFactory;


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewModelFactory = viewModelFactory;
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
            viewHandler.openView(Views.FRONTPAGE);

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

    /**
     * Create popup.
     *
     * @param title    the title
     * @param text     the text
     * @param duration the duration
     * @param position the position
     */
    public void createPopup(String title, String text, int duration, Pos position) {
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(duration))
                .position(position)
                .onAction(actionEvent -> LOGGER.info("Pressed popup"));
        notificationBuilder.show();
    }


    /**
     * Btn front page.
     */
    public void btnFrontPage() {
        viewHandler.openView(Views.FRONTPAGE);
    }

    /**
     * Btn productions.
     */
    public void btnProductions() {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
        viewModelFactory.getBrowseProductionsViewModel().setMail();
    }

    /**
     * Btn channels.
     */
    public void btnChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
        viewModelFactory.getBrowseChannelsViewModel().setMail();
    }


    /**
     * Btn search.
     */
    public void btnSearch() {
        viewHandler.openView(Views.FRONTPAGE);
    }
}