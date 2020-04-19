package dk.creditoro.client.networking.dummy_client;

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
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {

    }
}
