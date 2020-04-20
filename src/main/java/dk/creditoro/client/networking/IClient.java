package dk.creditoro.client.networking;

import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.User;

import java.beans.PropertyChangeListener;

/**
 * The interface Http manager.
 */
public interface IClient {
    String login(String email, String password);
    void register(User user);

    Channel[] searchChannels(String q, String token);

    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
