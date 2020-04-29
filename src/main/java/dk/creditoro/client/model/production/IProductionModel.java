package dk.creditoro.client.model.production;

import java.beans.PropertyChangeListener;

public interface IProductionModel {
    /**
     * Search.
     *
     * @param q the q
     */
    void search(String q, String token);

    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
