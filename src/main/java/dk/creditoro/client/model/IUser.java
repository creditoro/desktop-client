package dk.creditoro.client.model;

import dk.creditoro.client.model.http.IHttpManager;

/**
 * The interface Model.
 */
public interface IUser extends IHttpManager {

    /**
     * Login.
     *  @param username the username
     * @param password the password
     * @return
     */
    String login(String username, String password);
}
