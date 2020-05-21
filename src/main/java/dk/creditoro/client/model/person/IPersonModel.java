package dk.creditoro.client.model.person;

import java.beans.PropertyChangeListener;

public interface IPersonModel {

    void getPersons(String q);

    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
