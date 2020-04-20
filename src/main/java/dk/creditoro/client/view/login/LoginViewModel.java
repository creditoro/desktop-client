package dk.creditoro.client.view.login;

import dk.creditoro.client.model.user.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.util.logging.Logger;

/**
 * The type Login view model.
 */
public class LoginViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty loginResponse = new SimpleStringProperty();
    private final IUserModel userModel;

    /**
     * Instantiates a new Login view model.
     *
     * @param userModel the user model
     */
    public LoginViewModel(IUserModel userModel) {
        this.userModel = userModel;
        // "LoginResult", should be an enum instead of a magic string.
        this.userModel.addListener("LoginResult", (this::onLoginResponse));
        LOGGER.info("Added listener.");
    }

    /**
     * Username property string property.
     *
     * @return the string property
     */
    public StringProperty emailProperty() {
        return email;
    }

    /**
     * Password property string property.
     *
     * @return the string property
     */
    public StringProperty passwordProperty() {
        return password;
    }

    /**
     * Result property string property.
     *
     * @return the string property
     */
    public StringProperty loginResponseProperty() {
        return loginResponse;
    }

    private void onLoginResponse(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On login response called.");
        String result = (String) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> loginResponse.setValue(result));
    }

    /**
     * Clear fields.
     */
    public void clearFields() {
        email.setValue("");
        password.setValue("");
    }


    /**
     * Login.
     */
    public void login() {
        userModel.login(email.get(), password.get());
    }
}