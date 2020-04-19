package dk.creditoro.client.networking.rest_client;


import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.endpoints.HttpManager;
import dk.creditoro.client.networking.rest_client.endpoints.UsersEndpoint;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

/**
 * The type Http manager.
 */
public class RestClient implements IClient {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final PropertyChangeSupport propertyChangeSupport;
    private final UsersEndpoint usersEndpoint;

    public RestClient() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        usersEndpoint = new UsersEndpoint(new HttpManager());
    }

    @Override
    public String login(String email, String password) {
        var result = usersEndpoint.postLogin(email, password);
        propertyChangeSupport.firePropertyChange("LoginResult", null, result);
        return result;
    }

    @Override
    public void register(User user) {
        LOGGER.info("Called register.");
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        LOGGER.info("added listener.");
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }
}