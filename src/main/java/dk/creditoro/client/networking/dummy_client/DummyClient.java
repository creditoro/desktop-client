package dk.creditoro.client.networking.dummy_client;

import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.IClient;

import java.beans.PropertyChangeListener;

public class DummyClient implements IClient {
    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public void register(User user) {
        return;
    }

    @Override
    public Channel[] searchChannels(String q) {
        return new Channel[0];
    }

    @Override
    public Production[] searchProductions(String q) {
        return new Production[0];
    }

    @Override
    public Credit[] searchCredits(String q) {
        return new Credit[0];
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
    }
}
