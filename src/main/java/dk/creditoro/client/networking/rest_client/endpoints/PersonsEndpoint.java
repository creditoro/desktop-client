package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Person;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

/**
 * The type Persons endpoint.
 */
public class PersonsEndpoint {
    private static final String PERSONS = "/people/";
    private final HttpManager httpManager;

    /**
     * Instantiates a new Persons endpoint.
     *
     * @param httpManager the http manager
     */
    public PersonsEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    /**
     * Gets person.
     *
     * @param identifier the identifier
     * @return the person
     */
    public TokenResponse<Person> getPerson(String identifier) {
        var response = httpManager.get(PERSONS, identifier, null);
        return new TokenResponse<>(response.asObject(Person.class));
    }

    /**
     * Put person token response.
     *
     * @param identifier the identifier
     * @param body       the body
     * @return the token response
     */
    public TokenResponse<Person> putPerson(String identifier, JSONObject body) {
        return null;
    }

    /**
     * Patch person token response.
     *
     * @param identifier the identifier
     * @param fields     the fields
     * @return the token response
     */
    public TokenResponse<Person> patchPerson(String identifier, Map<String, Object> fields) {
        return null;
    }

    /**
     * Gets persons.
     *
     * @param q     the q
     * @param token the token
     * @return the persons
     */
    public TokenResponse<Person[]> getPersons(String q, String token) {
        var response = httpManager.getList(PERSONS, q, token);
        return new TokenResponse<>(response.asObject(Person[].class));
    }

    /**
     * Post person token response.
     *
     * @param body the body
     * @return the token response
     */
    public TokenResponse<Person> postPerson(Person person, String token) {
        var response = httpManager.post(PERSONS, person, token);
        return new TokenResponse<>(response.asObject(Person.class));
    }
}
