package dk.creditoro.client.model.channel;

import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.networking.IClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

/**
 * The type User.
 */
public class ChannelModel implements IChannelModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IClient client;
    private final PropertyChangeSupport propertyChangeSupport;


    /**
     * Instantiates a new User model.
     *
     * @param client the client
     */
    public ChannelModel(IClient client) {
        this.client = client;
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.client.addListener("onSearchChannelsResult", this::onSearchChannelsResult);
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(name, propertyChangeListener);
    }

    @Override
    public void search(String q, String token) {
        Channel[] channels = client.searchChannels(q, token);
        var message = String.format("channels found: %d", channels.length);
        LOGGER.info(message);
    }

    public void onSearchChannelsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search channels result called.");
        var channels = (Channel[]) propertyChangeEvent.getNewValue();
        propertyChangeSupport.firePropertyChange("kek", null, channels);
    }
}