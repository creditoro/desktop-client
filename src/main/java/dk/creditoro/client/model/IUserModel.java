package dk.creditoro.client.model;

/**
 * The interface Model.
 */
public interface IUserModel {

    /**
     * Login.
     *
     * @param email    the username
     * @param password the password
     * @return
     */
    String login(String email, String password);
}
