package dk.creditoro.client.model.person;

import dk.creditoro.client.core.EventNames;
import dk.creditoro.client.model.crud.Person;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.TokenResponse;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

/**
 * The type Person model.
 */
public class PersonModel implements IPersonModel {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IClient client;
    private final PropertyChangeSupport propertyChangeSupport;


    /**
     * Instantiates a new Person model.
     *
     * @param client the client
     */
    public PersonModel(IClient client) {
        this.client = client;
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.client.addListener(EventNames.ON_SEARCH_PEOPLE_RESULT.toString(), this::onSearchPersonsResult);
    }

    @Override
    public Person[] getPeople(String q) {
        Person[] people = client.getPeople(q);
        var message = String.format("persons found: %d", people.length);
        LOGGER.info(message);

        return people;
    }

    @Override
    public void postPerson(Person person) {
        client.postPerson(person);
        var message = String.format("person posted %d", 1);
        LOGGER.info(message);
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }

    /**
     * On search persons result.
     *
     * @param propertyChangeEvent the property change event
     */
    @SuppressWarnings("unchecked")
    public void onSearchPersonsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search persons result called.");
        var response = (TokenResponse<Person[]>) propertyChangeEvent.getNewValue();
        var persons = response.getT();
        propertyChangeSupport.firePropertyChange("kek", null, persons);
    }
}
