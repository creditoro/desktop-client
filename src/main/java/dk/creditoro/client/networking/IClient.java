package dk.creditoro.client.networking;

import dk.creditoro.client.model.crud.*;

import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * The interface Http manager.
 */
public interface IClient {
    User login(String email, String password);
    void register(User user);

    Channel[] searchChannels(String q);

    Production[] searchProductions(String q);
  
    Person[] getPeople(String q);

    Person postPerson(Person person);

    Credit[] getCredits(String q);

    Credit postCredits(Credit credit);

    Credit patchCredits(String identifier , Map<String, Object> fields);

    boolean deleteCredit(String identifier);

    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
