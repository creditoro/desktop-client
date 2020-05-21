package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class ChannelsEndpoint {

    private static final String CHANNELS = "/channels/";
    private final HttpManager httpManager;


    public ChannelsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public TokenResponse<Channel> getChannel(String identifier) {
        var response = httpManager.get("/channels", identifier, null);
        return new TokenResponse<>(response.asObject(Channel.class));
    }

    public TokenResponse<Channel> putChannel(String identifier, JSONObject body, String token) {
        var response = httpManager.put(CHANNELS, identifier, body, token);
        return new TokenResponse<>(response.asObject(Channel.class));
    }

    public TokenResponse<Channel> patchChannel(String identifier, Map<String, Object> fields, String token) {
        var response = httpManager.patch(CHANNELS, identifier, fields, token);
        return new TokenResponse<>(response.asObject(Channel.class));
    }

    public TokenResponse<Channel[]> getChannels(String q, String token) {
        var response = httpManager.getList(CHANNELS, q, token);
        return new TokenResponse<>(response.asObject(Channel[].class));
    }

    public TokenResponse<Channel> postChannel(JSONObject body, String token) {
        var response = httpManager.post(CHANNELS, body, token);
        return new TokenResponse<>(response.asObject(Channel.class));
    }

    public boolean deleteChannel(String identifier, String token){
        var response = httpManager.delete("channels", identifier, token);
        return response.asEmpty().isSuccess();
    }

}
