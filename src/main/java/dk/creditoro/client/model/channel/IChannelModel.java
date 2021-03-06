package dk.creditoro.client.model.channel;

import java.beans.PropertyChangeListener;

/**
 * The interface Model.
 */
public interface IChannelModel {
    /**
     * Search.
     *
     * @param q the q
     */
    void search(String q);


    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);

}
