package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Channel;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class ChannelsEndpoint {

    private final HttpManager httpManager;


    public ChannelsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public Channel getChannel(String identifier) {
        var response = httpManager.get("/channels", identifier, null);
        return response.asObject(Channel.class).getBody();
    }

    public Channel putChannel(String identifier, JSONObject body) {
        return null;
    }

    public Channel patchChannel(String identifier, Map<String, Object> fields) {
        return null;
    }

    public Channel[] getChannels(String q, String token) {
        var response = httpManager.getList("/channels/", q, token);
        return response.asObject(Channel[].class).getBody();
    }

    public Channel postChannel(JSONObject body) {
        var response = httpManager.post("/users", body);
        return response.asObject(Channel.class).getBody();
    }
}
