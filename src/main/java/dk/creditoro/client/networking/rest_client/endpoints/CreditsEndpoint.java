package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class CreditsEndpoint {

    private static final String CREDITS = "/credits/";
    private final HttpManager httpManager;

    public CreditsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public TokenResponse<Credit> getCredit(String identifier) {
        var response = httpManager.get(CREDITS, identifier, null);
        return new TokenResponse<>(response.asObject(Credit.class));
    }

    public TokenResponse<Credit> putCredit(String identifier, JSONObject body, String token) {
        var response = httpManager.put(CREDITS, identifier, body, token);
        return new TokenResponse<>(response.asObject(Credit.class));
    }

    public TokenResponse<Credit> patchCredit(String identifier, Map<String, Object> fields, String token) {
        var response = httpManager.patch(CREDITS, identifier, fields, token);
        return new TokenResponse<>(response.asObject(Credit.class));
    }

    public TokenResponse<Credit[]> getCredits(String id, String token) {
        var response = httpManager.getCredits(CREDITS, id, token);
        return new TokenResponse<>(response.asObject(Credit[].class));
    }

    public TokenResponse<Credit> postCredit(Credit credit, String token) {
        var response = httpManager.post(CREDITS, credit, token);
        return new TokenResponse<>(response.asObject(Credit.class));
    }
}
