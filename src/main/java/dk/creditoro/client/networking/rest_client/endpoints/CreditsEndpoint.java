package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

/**
 * The type Credits endpoint.
 */
public class CreditsEndpoint {

    private static final String CREDITS = "/credits/";
    private final HttpManager httpManager;

    /**
     * Instantiates a new Credits endpoint.
     *
     * @param httpManager the http manager
     */
    public CreditsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    /**
     * Gets credit.
     *
     * @param identifier the identifier
     * @return the credit
     */
    public TokenResponse<Credit> getCredit(String identifier) {
        var response = httpManager.get(CREDITS, identifier, null);
        return new TokenResponse<>(response.asObject(Credit.class));
    }

    /**
     * Put credit token response.
     *
     * @param identifier the identifier
     * @param body       the body
     * @param token      the token
     * @return the token response
     */
    public TokenResponse<Credit> putCredit(String identifier, JSONObject body, String token) {
        var response = httpManager.put(CREDITS, identifier, body, token);
        return new TokenResponse<>(response.asObject(Credit.class));
    }

    /**
     * Patch credit token response.
     *
     * @param identifier the identifier
     * @param fields     the fields
     * @param token      the token
     * @return the token response
     */
    public TokenResponse<Credit> patchCredit(String identifier, Map<String, Object> fields, String token) {
        var response = httpManager.patch(CREDITS, identifier, fields, token);
        return new TokenResponse<>(response.asObject(Credit.class));
    }

    /**
     * Gets credits.
     *
     * @param id    the id
     * @param token the token
     * @return the credits
     */
    public TokenResponse<Credit[]> getCredits(String id, String token) {
        Map<String, Object> queryParam = Map.of("production_id", id);
        var response = httpManager.getByQueryParams(CREDITS, queryParam, token);
        return new TokenResponse<>(response.asObject(Credit[].class));
    }

    /**
     * Post credit token response.
     *
     * @param credit the credit
     * @param token  the token
     * @return the token response
     */
    public TokenResponse<Credit> postCredit(Credit credit, String token) {
        var response = httpManager.post(CREDITS, credit, token);
        return new TokenResponse<>(response.asObject(Credit.class));
    }
}
