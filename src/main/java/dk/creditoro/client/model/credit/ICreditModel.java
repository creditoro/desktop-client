package dk.creditoro.client.model.credit;

import dk.creditoro.client.model.crud.Credit;

import java.beans.PropertyChangeListener;

/**
 * The interface Credit model.
 */
public interface ICreditModel {

    /**
     * Search.
     *
     * @param q the q
     */
    void getCredits(String q);

    /**
     * Post credits.
     *
     * @param credit the credit
     */
    void postCredits(Credit credit);

    /**
     * Add listener.
     *
     * @param name                   the name
     * @param propertyChangeListener the property change listener
     */
    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
