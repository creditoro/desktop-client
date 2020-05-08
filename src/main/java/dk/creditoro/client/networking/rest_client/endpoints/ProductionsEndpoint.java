package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class ProductionsEndpoint {

    private static final String PRODUCTIONS = "/productions/";
    private final HttpManager httpManager;


    public ProductionsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public TokenResponse<Production> getProduction(String identifier) {
        var response = httpManager.get(PRODUCTIONS, identifier, null);
        return new TokenResponse<>(response.asObject(Production.class));
    }

    public TokenResponse<Production> putProduction(String identifier, JSONObject body) {
        return null;
    }

    public TokenResponse<Production> patchProduction(String identifier, Map<String, Object> fields) {
        return null;
    }

    public TokenResponse<Production[]> getProductions(String q, String token) {
        var response = httpManager.getList(PRODUCTIONS, q, token);
        return new TokenResponse<>(response.asObject(Production[].class));
    }

    public TokenResponse<Production> postProduction(JSONObject body) {
        var response = httpManager.post(PRODUCTIONS, body);
        return new TokenResponse<>(response.asObject(Production.class));
    }
}
