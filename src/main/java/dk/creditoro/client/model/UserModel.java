package dk.creditoro.client.model;

import dk.creditoro.client.model.http.HttpManager;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.logging.Logger;

/**
 * The type User.
 */
public class UserModel extends HttpManager implements IUserModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    @Override
    public String login(String email, String password) {
        JSONObject a = new JSONObject(Map.of("email", email, "password", password));
        var response = this.post("users/login", a);
        var status = response.getStatus();
        if (status != 200) {
            var output = String.format("status code was: %d", status);
            LOGGER.info(output);
            return "";
        }
        return response.getBody().getObject().getString("token");
    }
}