package dk.creditoro.client.model.http;


import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestConfigException;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.logging.Logger;

/**
 * The type Http manager.
 */
public class HttpManager implements IHttpManager {
    private static final String AUTH_HEADER = "Authorization";
    private static final String IDENTIFIER = "identifier";
    private static final String PATH = "/%s/{%s}";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     * The Token.
     */
    private String token;


    /**
     * Instantiates a new Http manager.
     */
    public HttpManager() {
        try {
            Unirest.config()
                    .setDefaultHeader("Accept", "application/json")
                    .setDefaultHeader("Content-Type", "application/json")
                    .followRedirects(true)
                    .enableCookieManagement(false)
                    .defaultBaseUrl("https://api.creditoro.nymann.dev");
        } catch (UnirestConfigException e) {
            LOGGER.info("Unirest is already configured, skipping.");
        }
    }


    @Override
    public HttpResponse<JsonNode> get(String route, String identifier) {
        return Unirest.get(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> getList(String route, String q) {
        return Unirest.get(String.format("/%s", route))
                .queryString("q", q)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> put(String route, String identifier, JSONObject body) {
        return Unirest.put(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .body(body)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> patch(String route, String identifier, Map<String, Object> fields) {
        return Unirest.patch(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .fields(fields)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> post(String route, JSONObject body) {
        return Unirest.post(String.format("/%s", route))
                .body(body)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> delete(String route, String identifier) {
        return Unirest.delete(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    public void setToken(String token) {
        this.token = token;
    }
}