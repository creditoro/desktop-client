package dk.creditoro.client.networking;

import dk.creditoro.client.model.crud.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * The interface Http manager.
 */
public interface IClient {
    String login(String email, String password);
    void register(User user);

    void addListener(String name, PropertyChangeListener propertyChangeListener);
}
