package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Production;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class ProductionsEndpoint {

	private static final String PRODUCTIONS = "/productions/";
    private final HttpManager httpManager;


    public ProductionsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public Production getProduction(String identifier) {
        var response = httpManager.get(PRODUCTIONS, identifier, null);
        return response.asObject(Production.class).getBody();
    }

    public Production putProduction(String identifier, JSONObject body) {
        return null;
    }

    public Production patchProduction(String identifier, Map<String, Object> fields) {
        return null;
    }

    public Production[] getProductions(String q, String token) {
        var response = httpManager.getList(PRODUCTIONS, q, token);
        return response.asObject(Production[].class).getBody();
    }

    public Production postProduction(JSONObject body) {
        var response = httpManager.post(PRODUCTIONS, body);
        return response.asObject(Production.class).getBody();
    }

    public Production postProduction(JSONObject body, String token) {
        var response = httpManager.post(PRODUCTIONS, token, body);
        return response.asObject(Production.class).getBody();
    }
}
