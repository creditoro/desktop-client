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
     * @param user     the user
     * @param password the password
     */
    void register(User user, String password);

    /**
     * Gets current user.
     *
     * @return the current user
     */
    User getCurrentUser();

    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
