package dk.creditoro.client.networking.rest_client;


import dk.creditoro.client.core.EventNames;
import dk.creditoro.client.model.crud.*;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.endpoints.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The type Rest client.
 */
public class RestClient implements IClient {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final PropertyChangeSupport propertyChangeSupport;
    private final UsersEndpoint usersEndpoint;
    private final ChannelsEndpoint channelsEndpoint;
    private final ProductionsEndpoint productionsEndpoint;
    private final CreditsEndpoint creditsEndpoint;
    private final PersonsEndpoint personsEndpoint;
    private static final String PROPERTY_CHANGE = "Fired property change event. ";

    private String token;

    /**
     * Instantiates a new Rest client.
     */
    public RestClient() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        var httpManager = new HttpManager();
        usersEndpoint = new UsersEndpoint(httpManager);
        channelsEndpoint = new ChannelsEndpoint(httpManager);
        productionsEndpoint = new ProductionsEndpoint(httpManager);
        creditsEndpoint = new CreditsEndpoint(httpManager);
        personsEndpoint = new PersonsEndpoint(httpManager);
    }

    @Override
    public User login(String email, String password) {
        var result = usersEndpoint.postLogin(email, password);
        propertyChangeSupport.firePropertyChange(EventNames.LOGIN_RESULT.toString(), null, result);
        updateToken(result);
        return result.getT();
    }

    @Override
    public void register(User user) {
        LOGGER.info("Called register.");
    }

    @Override
    public Channel[] searchChannels(String q) {
        var result = channelsEndpoint.getChannels(q, token);
        propertyChangeSupport.firePropertyChange(EventNames.ON_SEARCH_CHANNELS_RESULT.toString(), null, result);
        LOGGER.info(PROPERTY_CHANGE);
        updateToken(result);
        return result.getT();
    }

    @Override
    public Production[] searchProductions(String q) {
        var result = productionsEndpoint.getProductions(q, token);
        propertyChangeSupport.firePropertyChange(EventNames.ON_SEARCH_PRODUCTIONS_RESULT.toString(), null, result);
        LOGGER.info(PROPERTY_CHANGE);
        updateToken(result);
        return result.getT();
    }

    // These are for credits
    @Override
    public Credit[] getCredits(String id) {
        var result = creditsEndpoint.getCredits(id, token);
        propertyChangeSupport.firePropertyChange(EventNames.ON_SEARCH_CREDITS_RESULT.toString(), null, result);
        LOGGER.info(PROPERTY_CHANGE);
        updateToken(result);
        return result.getT();
    }

    @Override
    public Credit patchCredits(String identifier , Map<String, Object> fields){
        var result = creditsEndpoint.patchCredit(identifier, fields, token);
        propertyChangeSupport.firePropertyChange(EventNames.ON_PATCH_CREDITS_RESULT.toString(), null, result);
        LOGGER.info(PROPERTY_CHANGE);
        updateToken(result);
        return result.getT();
    }

    @Override
    public Credit postCredits(Credit credit){
        var result = creditsEndpoint.postCredit(credit, token);
        propertyChangeSupport.firePropertyChange(EventNames.ON_POST_CREDITS_RESULT.toString(), null, result);
        LOGGER.info(PROPERTY_CHANGE);
        updateToken(result);
        return result.getT();
    }

    @Override
    public Person[] getPersons(String q) {
        var result = personsEndpoint.getPersons(q, token);
        propertyChangeSupport.firePropertyChange(EventNames.ON_SEARCH_PERSONS_RESULT.toString(), null, result);
        LOGGER.info(PROPERTY_CHANGE);
        updateToken(result);
        return result.getT();
    }

    @Override
    public Person postPerson(Person person) {
        var result = personsEndpoint.postPerson(person, token);
        propertyChangeSupport.firePropertyChange(EventNames.ON_POST_PERSON_RESULT.toString(), null, result);
        LOGGER.info(PROPERTY_CHANGE);
        updateToken(result);
        return result.getT();
    }


    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        LOGGER.info("added listener.");
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }

    private void updateToken(TokenResponse<?> response) {
        token = response.getToken();
    }
}
