package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Person;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

public class PersonsEndpoint {
    private static final String PERSONS = "/people/";
    private final HttpManager httpManager;

    public PersonsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public TokenResponse<Person> getPerson(String identifier) {
        var response = httpManager.get(PERSONS, identifier, null);
        return new TokenResponse<>(response.asObject(Person.class));
    }

    public TokenResponse<Person> putPerson(String identifier, JSONObject body) {
        return null;
    }

    public TokenResponse<Person> patchPerson(String identifier, Map<String, Object> fields) {
        return null;
    }

    public TokenResponse<Person[]> getPersons(String q, String token) {
        var response = httpManager.getList(PERSONS, q, token);
        return new TokenResponse<>(response.asObject(Person[].class));
    }

    public TokenResponse<Person> postPerson(JSONObject body) {
        var response = httpManager.post(PERSONS, body);
        return new TokenResponse<>(response.asObject(Person.class));
    }
}
