package dk.creditoro.client.networking.dummy_client;

import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.IClient;

import java.beans.PropertyChangeListener;

public class DummyClient implements IClient {
    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public Channel[] searchChannels(String q, String token) {
        return new Channel[0];
    }

    @Override
    public Production[] searchProductions(String q, String token) {
        return new Production[0];
    }

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {

    }
}
