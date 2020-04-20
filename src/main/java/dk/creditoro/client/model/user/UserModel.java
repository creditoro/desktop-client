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
    private User currentUser;

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
    public User getCurrentUser() {
        return currentUser;
    }

    public String getToken() {
        return currentUser.getToken();
    }

    @Override
    public void login(String email, String password) {
        currentUser = new User(email, password);
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

    private void onLoginResult(PropertyChangeEvent propertyChangeEvent) {
        String loginResult = (String) propertyChangeEvent.getNewValue();
        if (loginResult == null) {
            LOGGER.info("Couldn't log in.");
            propertyChangeSupport.firePropertyChange("LoginResult", null, "Incorrect Login credentials try again");
        } else {
            currentUser.setToken(loginResult);
            var message = String.format("token: '%s'", currentUser);
            propertyChangeSupport.firePropertyChange("LoginResult", null, message);
        }
    }
}