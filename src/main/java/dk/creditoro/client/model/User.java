package dk.creditoro.client.model;

import dk.creditoro.client.model.http.HttpManager;

import java.util.Map;

/**
 * The type User.
 */
public class User extends HttpManager implements IUser {

    private String identifier;
    private String phone;
    private String email;
    private String password;
    private String name;
    private String role;

    public User() {

    }

    @Override
    public String login(String username, String password) {
        var response = this.post("users", Map.of("username", username, "password", password));
        var status = response.getStatus();
        if(status != 200) {
            return "";
        }
        return response.getBody().getObject().getString("token");
    }
}