package dk.creditoro.client.model.http;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

import java.util.Map;

/**
 * The interface Http manager.
 */
public interface IHttpManager {
    /**
     * Get.
     *
     * @param route      the route
     * @param identifier the identifier
     * @return the string
     */
    HttpResponse<JsonNode> get(String route, String identifier);

    /**
     * Gets list.
     *
     * @param route the route
     * @param q     the q
     * @return the list
     */
    HttpResponse<JsonNode> getList(String route, String q);

    /**
     * Put.
     *
     * @param route      the route
     * @param identifier the identifier
     * @param body       the body
     * @return the string
     */
    HttpResponse<JsonNode> put(String route, String identifier, JSONObject body);

    /**
     * Patch.
     *
     * @param route      the route
     * @param identifier the identifier
     * @param fields     the fields
     * @return the string
     */
    HttpResponse<JsonNode> patch(String route, String identifier, Map<String, Object> fields);

    /**
     * Post.
     *
     * @param route the route
     * @param json  the json
     * @return the string
     */
    HttpResponse<JsonNode> post(String route, JSONObject json);

    /**
     * Delete.
     *
     * @param route      the route
     * @param identifier the identifier
     * @return the string
     */
    HttpResponse<JsonNode> delete(String route, String identifier);
}
