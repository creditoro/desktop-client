package dk.creditoro.client.model.user;

import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.IClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

/**
 * The type User.
 */
public class UserModel implements IUserModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IClient client;
    private final PropertyChangeSupport propertyChangeSupport;
    private String token;

    /**
     * Instantiates a new User model.
     *
     * @param client the client
     */
    public UserModel(IClient client) {
        this.client = client;
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.client.addListener("LoginResult", this::onLoginResult);
    }

    @Override
    public void login(String email, String password) {
        token = client.login(email, password);
        var message = String.format("%s logged in, and now has token: '%s'", email, token);
        LOGGER.info(message);
    }

    @Override
    public void register(User user) {
        LOGGER.info("called register.");
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }

    private void onLoginResult(PropertyChangeEvent propertyChangeEvent) {
        String loginResult = (String) propertyChangeEvent.getNewValue();
        if (loginResult.isEmpty()) {
            LOGGER.info("Couldn't log in.");
        }

        propertyChangeSupport.firePropertyChange("LoginResult", null, loginResult);
    }
}