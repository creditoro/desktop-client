package dk.creditoro.client.model;

import dk.creditoro.client.model.http.HttpManager;

import java.util.Map;

/**
 * The type User.
 */
public class UserModel extends HttpManager implements IUserModel {

    private String identifier;
    private String phone;
    private String email;
    private String password;
    private String name;
    private String role;

    public UserModel() {

    }

    @Override
    public String login(String email, String password) {
        var response = this.post("users/", Map.of("email", email, "password", password));
        var status = response.getStatus();
        if(status != 200) {
            System.out.println(status);
            return "";
        }
        return response.getBody().getObject().getString("token");
    }
}