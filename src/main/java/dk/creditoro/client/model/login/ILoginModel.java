package dk.creditoro.client.model.login;

import java.beans.PropertyChangeListener;

/**
 * The interface Login model.
 */
public interface ILoginModel {
    /**
     * Add listener.
     *
     * @param name     the name
     * @param listener the listener
     */
    void addListener(String name, PropertyChangeListener listener);

    /**
     * Validate login boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    boolean validateLogin(String username, String password);
}
