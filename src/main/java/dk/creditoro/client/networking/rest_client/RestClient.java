package dk.creditoro.client.networking.rest_client;


import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.endpoints.ChannelsEndpoint;
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
    private final ChannelsEndpoint channelsEndpoint;

    public RestClient() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        var httpManager = new HttpManager();
        usersEndpoint = new UsersEndpoint(httpManager);
        channelsEndpoint = new ChannelsEndpoint(httpManager);
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
    public Channel[] searchChannels(String q, String token) {
        var result = channelsEndpoint.getChannels(q, token);
        propertyChangeSupport.firePropertyChange("onSearchChannelsResult", null, result);
        LOGGER.info("Fired property change event.");
        return result;
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        LOGGER.info("added listener.");
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }
}