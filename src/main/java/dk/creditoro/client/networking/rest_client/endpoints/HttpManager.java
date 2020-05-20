package dk.creditoro.client.networking.rest_client.endpoints;

import kong.unirest.*;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.logging.Logger;

public class HttpManager {
    private static final String AUTH_HEADER = "Authorization";
    private static final String IDENTIFIER = "identifier";
    private static final String PATH = "/%s/{%s}";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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


    public GetRequest get(String route, String identifier, String token) {
        return Unirest.get(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .header(AUTH_HEADER, token);
    }

    public GetRequest getList(String route, String q, String token) {
        return Unirest.get(route)
                .queryString("q", q)
                .header(AUTH_HEADER, token);
    }

    public GetRequest getCredits(String route, String id, String token)
    {
        return Unirest.get(route)
                .queryString("production_id", id)
                .header(AUTH_HEADER, token);
    }

    public RequestBodyEntity put(String route, String identifier, JSONObject body, String token) {
        return Unirest.put(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .body(body)
                .header(AUTH_HEADER, token);
    }

    public MultipartBody patch(String route, String identifier, Map<String, Object> fields, String token) {
        return Unirest.patch(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .fields(fields)
                .header(AUTH_HEADER, token);
    }

    public RequestBodyEntity post(String route, JSONObject body) {
        return Unirest.post(String.format("/%s", route))
                .body(body);
    }

    public RequestBodyEntity post(String route, String token, JSONObject body) {
        return Unirest.post(String.format("/%s", route))
                .header(AUTH_HEADER, token)
                .body(body);
    }

    public HttpRequestWithBody delete(String route, String identifier, String token) {
        return Unirest.delete(String.format(PATH, route, IDENTIFIER))
                .routeParam(IDENTIFIER, identifier)
                .header(AUTH_HEADER, token);
    }
}
