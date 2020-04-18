package dk.creditoro.client.model.http;


import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.Map;
import java.util.logging.Logger;

/**
 * The type Http manager.
 */
public class HttpManager implements IHttpManager {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String AUTH_HEADER = "Authorization";
    /**
     * The Token.
     */
    public String token;

    /**
     * Instantiates a new Http manager.
     */
    public HttpManager() {
        Unirest.config()
                .setDefaultHeader("Accept", "application/json")
                .setDefaultHeader("Content-Type", "application/json")
                .followRedirects(true)
                .enableCookieManagement(false)
                .defaultBaseUrl("https://api.creditoro.nymann.dev");
    }

    @Override
    public HttpResponse<JsonNode> get(String route, String identifier) {
        return Unirest.get(String.format("/%s/{identifier}", route))
                .routeParam("identifier", identifier)
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
    public HttpResponse<JsonNode> put(String route, String identifier, Map<String, Object> json) {
        return Unirest.put(String.format("/%s/{identifier}", route))
                .routeParam("identifier", identifier)
                .fields(json)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> patch(String route, String identifier, Map<String, Object> json) {
        return Unirest.patch(String.format("/%s/{identifier}", route))
                .routeParam("identifier", identifier)
                .fields(json)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> post(String route, Map<String, Object> json) {
        return Unirest.post(String.format("/%s", route))
                .body(json)
                .header(AUTH_HEADER, token)
                .asJson();
    }

    @Override
    public HttpResponse<JsonNode> delete(String route, String identifier) {
        return Unirest.delete(String.format("/%s/{identifier}", route))
                .routeParam("identifier", identifier)
                .header(AUTH_HEADER, token)
                .asJson();
    }
}