package dk.creditoro.client.model.login;

import dk.creditoro.client.model.http.HttpManager;
import dk.creditoro.client.model.http.IHttpManager;
import kong.unirest.json.JSONObject;
import org.apache.http.HttpStatus;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The type Login model.
 */
public class LoginModel implements ILoginModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final IHttpManager httpManager = new HttpManager();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String checkCredentials(String email, String password) {
        JSONObject a = new JSONObject(Map.of("email", email, "password", password));
        var response = httpManager.post("users/login", a);
        var status = response.getStatus();
        switch (status) {
            case HttpStatus.SC_OK:
                LOGGER.info(response.getBody().getObject().getString("token"));
                return "OK";
            case HttpStatus.SC_NOT_FOUND:
                var user = String.format("%s not found.", email);
                LOGGER.info(user);
                return "User not found";
            case HttpStatus.SC_BAD_REQUEST:
                return "Incorrect password";
            default:
                var statusMessage = String.format("%d, uncaught (email: %s).", status, email);
                LOGGER.info(statusMessage);
                return "Uncaught error.";
        }
    }

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public boolean validateLogin(String username, String password) {
        String result = checkCredentials(username, password);
        support.firePropertyChange("LoginResult", "", result);
        return result.equals("OK");
    }
}
