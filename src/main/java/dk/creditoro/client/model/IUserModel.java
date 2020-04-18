package dk.creditoro.client.model;

import dk.creditoro.client.model.http.IHttpManager;

/**
 * The interface Model.
 */
public interface IUserModel extends IHttpManager {

    /**
     * Login.
     *  @param email the username
     * @param password the password
     * @return
     */
    String login(String email, String password);
}
