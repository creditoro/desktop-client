package dk.creditoro.client.model.user;

import dk.creditoro.client.model.crud.User;

import java.beans.PropertyChangeListener;

/**
 * The interface Model.
 */
public interface IUserModel {
    /**
     * Login.
     *
     * @param email    the email
     * @param password the password
     */
    void login(String email, String password);

    /**
     * Register.
     *
     * @param user the user
     */
    void register(User user);

    User getCurrentUser();

    String getToken();

    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
