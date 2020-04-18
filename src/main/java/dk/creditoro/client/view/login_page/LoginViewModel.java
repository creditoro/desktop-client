package dk.creditoro.client.view.login_page;

import dk.creditoro.client.model.IUserModel;
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

    private StringProperty email;
    private StringProperty password;

    private IUserModel model;


    /**
     * Instantiates a new Login view model.
     *
     * @param model the model
     */
    public LoginViewModel(IUserModel model) {
        this.model = model;
        email = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    /**
     * Sign in.
     *
     * @param email the username
     * @param password the password
     */
    public void signIn(String email, String password) {
        var response = model.login(email, password);
        System.out.println(response);
    }

}