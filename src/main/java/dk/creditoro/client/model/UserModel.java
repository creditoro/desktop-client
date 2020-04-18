package dk.creditoro.client.model;

import dk.creditoro.client.model.http.HttpManager;
import dk.creditoro.client.model.http.IHttpManager;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.logging.Logger;

/**
 * The type User.
 */
public class UserModel implements IUserModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final IHttpManager httpManager = new HttpManager();

    @Override
    public String login(String email, String password) {
        JSONObject a = new JSONObject(Map.of("email", email, "password", password));
        var response = httpManager.post("users/login", a);
        var status = response.getStatus();
        if (status != 200) {
            var output = String.format("status code was: %d", status);
            LOGGER.info(output);
            return null;
        }
        return response.getBody().getObject().getString("token");
    }
}