package dk.creditoro.client.model.credit;

import dk.creditoro.client.core.EventNames;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

public class CreditModel implements ICreditModel {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IClient client;
    private final PropertyChangeSupport propertyChangeSupport;


    public CreditModel(IClient client) {
        this.client = client;
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.client.addListener(EventNames.ON_SEARCH_CREDITS_RESULT.toString(), this::onSearchCreditsResult);
    }

    @Override
    public void getCredits(String id) {
        Credit[] credits = client.getCredits(id);
        var message = String.format("credits found: %d", credits.length);
        LOGGER.info(message);
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }

    @SuppressWarnings("unchecked")
    public void onSearchCreditsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search credits result called.");
        var response = (TokenResponse<Credit[]>) propertyChangeEvent.getNewValue();
        var credits = response.getT();
        propertyChangeSupport.firePropertyChange("kek", null, credits);
    }
}
