package dk.creditoro.client.model.person;

import dk.creditoro.client.model.crud.Person;

import java.beans.PropertyChangeListener;

public interface IPersonModel {

    Person[] getPersons(String q);

    void postPerson(Person person);

    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
