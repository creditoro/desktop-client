package dk.creditoro.client.view.login_page;

import dk.creditoro.client.model.IUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The type Login view model.
 */
public class LoginViewModel {

    /**
     * Username property string property.
     *
     * @return the string property
     */
    public StringProperty usernameProperty() {
        return username;
    }

    /**
     * Password property string property.
     *
     * @return the string property
     */
    public StringProperty passwordProperty() {
        return password;
    }

    private StringProperty username;
    private StringProperty password;

    private IUser model;


    /**
     * Instantiates a new Login view model.
     *
     * @param model the model
     */
    public LoginViewModel(IUser model) {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    /**
     * Sign in.
     *
     * @param username the username
     * @param password the password
     */
    public void signIn(String username, String password) {
        System.out.println(String.format("username: %s, password: %s", username, password));
    }

}