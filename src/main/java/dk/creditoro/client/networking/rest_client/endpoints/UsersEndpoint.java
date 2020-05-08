package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class UsersEndpoint {

    private final HttpManager httpManager;


    public UsersEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public TokenResponse<User> getUser(String identifier) {
        var response = httpManager.get("/users", identifier, null);
        return new TokenResponse<>(response.asObject(User.class));
    }

    public TokenResponse<User> putUser(String identifier, JSONObject body) {
        return null;
    }

    public TokenResponse<User> patchUser(String identifier, Map<String, Object> fields) {
        return null;
    }

    public TokenResponse<User[]> getUsers(String q) {
        var response = httpManager.getList("/users", q, null);
        return new TokenResponse<>(response.asObject(User[].class));
    }

    public TokenResponse<User> postUser(JSONObject body) {
        var response = httpManager.post("/users", body);
        return new TokenResponse<>(response.asObject(User.class));
    }

    public TokenResponse<User> postLogin(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        var body = new JSONObject(Map.of("email", email, "password", password));
        var response = httpManager.post("/users/login", body);
        return new TokenResponse<>(response.asObject(User.class));
    }
}
