package dk.creditoro.client.model.production;

import dk.creditoro.client.core.EventNames;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.networking.IClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

public class ProductionModel implements IProductionModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IClient client;
    private final PropertyChangeSupport propertyChangeSupport;


    public ProductionModel(IClient client) {
        this.client = client;
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.client.addListener(EventNames.ON_SEARCH_PRODUCTIONS_RESULT.toString(), this::onSearchProductionsResult);
    }

    @Override
    public void search(String q, String token) {
        Production[] productions = client.searchProductions(q,token);
        var message = String.format("productions found: %d", productions.length);
        LOGGER.info(message);
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(name,propertyChangeListener);

    }

    public void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent){
        LOGGER.info("On search productions result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        propertyChangeSupport.firePropertyChange("kek",null,productions);
    }
}
