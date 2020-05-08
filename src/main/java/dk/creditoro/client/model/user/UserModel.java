package dk.creditoro.client.model.user;

import dk.creditoro.client.core.EventNames;
import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.TokenResponse;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

/**
 * The type User.
 */
public class UserModel implements IUserModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IClient client;
    private final PropertyChangeSupport propertyChangeSupport;
    private User currentUser;

    /**
     * Instantiates a new User model.
     *
     * @param client the client
     */
    public UserModel(IClient client) {
        this.client = client;
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.client.addListener(EventNames.LOGIN_RESULT.toString(), this::onLoginResult);
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    public String getToken() {
        return currentUser.getToken();
    }

    @Override
    public void login(String email, String password) {
        currentUser = new User(email, password); // TODO: remake to returned user.
        client.login(email, password);
    }

    @Override
    public void register(User user) {
        LOGGER.info("called register.");
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }

    @SuppressWarnings("unchecked")
    private void onLoginResult(PropertyChangeEvent propertyChangeEvent) {
        var response = (TokenResponse<User>) propertyChangeEvent.getNewValue();
        if (response.getStatusCode() != 200) {
            LOGGER.info("Couldn't log in.");
            propertyChangeSupport.firePropertyChange(EventNames.LOGIN_RESULT.toString(), null, Long.toString(System.currentTimeMillis()));
        } else {
            LOGGER.info(String.format("%s logged in successfully.", currentUser.getName()));
            propertyChangeSupport.firePropertyChange(EventNames.LOGIN_RESULT.toString(), null, "OK");
        }
    }
}