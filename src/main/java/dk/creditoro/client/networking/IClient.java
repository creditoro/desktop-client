package dk.creditoro.client.networking;

import dk.creditoro.client.model.crud.*;

import java.beans.PropertyChangeListener;

/**
 * The interface Http manager.
 */
public interface IClient {
    User login(String email, String password);
    void register(User user);

    Channel[] searchChannels(String q);

    Production[] searchProductions(String q);

    Credit[] getCredits(String q);

    Person[] getPersons(String q);

    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
