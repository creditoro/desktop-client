package dk.creditoro.client.networking;

import dk.creditoro.client.model.crud.*;

import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * The interface Http manager.
 */
public interface IClient {
    /**
     * Login user.
     *
     * @param email    the email
     * @param password the password
     * @return the user
     */
    User login(String email, String password);

    /**
     * Register.
     *
     * @param user the user
     */
    void register(User user);

    /**
     * Search channels channel [ ].
     *
     * @param q the q
     * @return the channel [ ]
     */
    Channel[] searchChannels(String q);

    /**
     * Search productions production [ ].
     *
     * @param q the q
     * @return the production [ ]
     */
    Production[] searchProductions(String q);

    /**
     * Get persons person [ ].
     *
     * @param q the q
     * @return the person [ ]
     */
    Person[] getPeople(String q);

    /**
     * Get persons by email person [ ].
     *
     * @param email the email
     * @return the person [ ]
     */
    Person[] getPeopleByEmail(String email);

    /**
     * Post person person.
     *
     * @param person the person
     * @return the person
     */
    Person postPerson(Person person);

    /**
     * Get credits credit [ ].
     *
     * @param q the q
     * @return the credit [ ]
     */
    Credit[] getCredits(String q);

    /**
     * Post credits credit.
     *
     * @param credit the credit
     * @return the credit
     */
    Credit postCredits(Credit credit);

    /**
     * Patch credits credit.
     *
     * @param identifier the identifier
     * @param fields     the fields
     * @return the credit
     */
    Credit patchCredits(String identifier , Map<String, Object> fields);

    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);

    /**
     * Gets token.
     *
     * @return the token
     */
    String getToken();
}
