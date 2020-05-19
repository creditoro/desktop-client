package dk.creditoro.client.model.credit;

import java.beans.PropertyChangeListener;

public interface ICreditModel {

    /**
     * Search.
     *
     * @param q the q
     */
    void getCredits(String q);

    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
