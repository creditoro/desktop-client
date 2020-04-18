package dk.creditoro.client.view.login;

import dk.creditoro.client.model.login.ILoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

/**
 * The type Login view model.
 */
public class LoginViewModel {
    private String token;

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
    public StringProperty loginResultProperty() {
        return loginResult;
    }

    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty loginResult = new SimpleStringProperty();

    private final ILoginModel loginModel;


    /**
     * Instantiates a new Login view model.
     *
     * @param loginModel the model
     */
    public LoginViewModel(ILoginModel loginModel) {
        this.loginModel = loginModel;
        this.loginModel.addListener("LoginResult", this::onLoginResult);
    }

    private void onLoginResult(PropertyChangeEvent propertyChangeEvent) {
        String result = (String) propertyChangeEvent.getNewValue();
        if (result.equals("OK")) {
            clearFields();
        }
        loginResult.setValue(result);
    }

    public void clearFields() {
        email.setValue("");
        password.setValue("");
    }


    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Validate login.
     */
    public void validateLogin() {
        loginModel.validateLogin(email.getValue(), password.getValue());
    }
}