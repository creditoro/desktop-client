package dk.creditoro.rest_client;

import dk.creditoro.exceptions.HttpStatusException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The type Http manager.
 */
public class HttpManager {
    private final HttpHost host;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Instantiates a new Http manager.
     *
     * @param hostname the hostname
     * @param port     the port
     * @param scheme   the scheme
     */
    public HttpManager(String hostname, int port, String scheme) {
        host = new HttpHost(hostname, port, scheme);
    }

    /**
     * Get string.
     *
     * @param path  the path
     * @param query the query
     * @return the string
     */
    public String get(String path, String query) {
        //Specify get request
        HttpGet request = new HttpGet(path + query);

        // execute the Http request and return the string value from the httpEntity
        return executeHttp(request);
    }

    /**
     * Get string.
     *
     * @param path  the path
     * @param query the query
     * @param token the token
     * @return the string
     */
    public String get(String path, String query, String token) {
        //Specify get request
        HttpGet get = new HttpGet(path + query);

        //Add Token
        get.addHeader(new BasicHeader("Authorization", token));

        // execute the Http request and return the string value from the httpEntity
        return executeHttp(get);
    }

    /**
     * Post string.
     *
     * @param path  the path
     * @param json  the json
     * @param token the token
     * @return the string
     */
    public String post(String path, JSONObject json, String token) {
        //Specify post request
        HttpPost request = new HttpPost(path);

        //Add Token
        request.addHeader(new BasicHeader("Authorization", token));
        //String Entity
        StringEntity postEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(postEntity);

        // execute the Http request and return the string value from the httpEntity
        return executeHttp(request);
    }

    /**
     * Post string.
     *
     * @param path the path
     * @param json the json
     * @return the string
     */
    public String post(String path, JSONObject json) {
        //Specify post request
        HttpPost request = new HttpPost(path);

        //String Entity
        StringEntity postEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);

        // Get all information together
        request.setEntity(postEntity);

        // execute the Http request and return the string value from the httpEntity
        return executeHttp(request);
    }


    /**
     * Execute http string.
     *
     * @param request the request
     * @return the string
     */
    public String executeHttp(HttpUriRequest request) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(host, request)) {
            // Execute Http
            int statusCode = response.getStatusLine().getStatusCode();
            // LOG statusCode
            if (statusCode < 200 || statusCode > 299) {
                throw new HttpStatusException("Failed with HTTP error code : " + statusCode);
            }
            return streamToString(response.getEntity());

        } catch (IOException | HttpStatusException e) {
            LOGGER.info(String.format("error: %s", e));
        }
        return null;
    }

    /**
     * Stream to string string.
     *
     * @param response the response
     * @return the string
     */
    public String streamToString(HttpEntity response) {
        try {
            return new BufferedReader(new InputStreamReader(response.getContent())).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            LOGGER.info(String.format("error: %s", e));
            return null;
        }
    }
}
