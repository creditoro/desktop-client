package dk.creditoro.client.networking.rest_client;

import kong.unirest.HttpResponse;

public class TokenResponse<T> {
    private final T t;
    private final String token;
    private final int statusCode;

    public TokenResponse(HttpResponse<T> response) {
        var headers = response.getHeaders();
        this.token = headers.getFirst("token");
        this.t = response.getBody();
        this.statusCode = response.getStatus();
    }

    public String getToken() {
        return token;
    }

    public T getT() {
        return t;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
