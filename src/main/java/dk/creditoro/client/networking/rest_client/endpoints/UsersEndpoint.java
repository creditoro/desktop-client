package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.User;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsersEndpoint {

    private final HttpManager httpManager;


    public UsersEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public User getUser(String identifier) {
        var response = httpManager.get("/users", identifier, null);
        return response.asObject(User.class).getBody();
    }

    public User putUser(String identifier, JSONObject body) {
        return null;
    }

    public User patchUser(String identifier, Map<String, Object> fields) {
        return null;
    }

    public List<User> getUsers(String q) {
        return new ArrayList<>();
    }

    public User postUser(JSONObject body) {
        var response = httpManager.post("/users", body);
        return response.asObject(User.class).getBody();
    }

    public String postLogin(String email, String password) {
        var body = new JSONObject(Map.of("email", email, "password", password));
        var response = httpManager.post("/users/login", body);
        return response.asJson().getBody().getObject().getString("token");
    }
}
