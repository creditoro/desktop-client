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

    public TokenResponse<Channel> putChannel(String identifier, JSONObject body) {
        return null;
    }

    public TokenResponse<Channel> patchChannel(String identifier, Map<String, Object> fields) {
        return null;
    }

    public TokenResponse<Channel[]> getChannels(String q, String token) {
        var response = httpManager.getList(CHANNELS, q, token);
        return new TokenResponse<>(response.asObject(Channel[].class));
    }

	// This is never gonna work as it is setup now? - kevin 06-05-2020
    public TokenResponse<Channel> postChannel(JSONObject body) {
        var response = httpManager.post(CHANNELS, body);
        return new TokenResponse<>(response.asObject(Channel.class));

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
