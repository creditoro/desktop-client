package dk.creditoro.client.networking.rest_client.endpoints;

import dk.creditoro.client.model.crud.Person;
import dk.creditoro.client.networking.rest_client.TokenResponse;
import kong.unirest.json.JSONObject;

import java.util.Map;

/**
 * The type Persons endpoint.
 */
public class PeopleEndpoint {
    private static final String PEOPLE = "/people/";
    private final HttpManager httpManager;

    /**
     * Instantiates a new Persons endpoint.
     *
     * @param httpManager the http manager
     */
    public PeopleEndpoint(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    /**
     * Gets person.
     *
     * @param identifier the identifier
     * @return the person
     */
    public TokenResponse<Person> getPerson(String identifier) {
        var response = httpManager.get(PEOPLE, identifier, null);
        return new TokenResponse<>(response.asObject(Person.class));
    }

    /**
     * Gets person by email.
     *
     * @param email the email
     * @param token the token
     * @return the person by email
     */
    public TokenResponse<Person[]> getPersonByEmail(String email, String token) {
        Map<String, Object> queryParam = Map.of("email", email);
        var response = httpManager.getByQueryParams(PEOPLE, queryParam, token);
        return new TokenResponse<>(response.asObject(Person[].class));
    }

      /**
     * Put person token response.
     *
     * @param identifier the identifier
     * @param body       the body
     * @return the token response
     */
    public TokenResponse<Person> putPerson(String identifier, JSONObject body, String token) {
        var response = httpManager.put(PEOPLE, identifier, body, token);
        return new TokenResponse<>(response.asObject(Person.class));
    }


    /**
     * Patch person token response.
     *
     * @param identifier the identifier
     * @param fields     the fields
     * @return the token response
     */  
    public TokenResponse<Person> patchPerson(String identifier, Map<String, Object> fields, String token) {
        var response = httpManager.patch(PEOPLE, identifier, fields, token);
        return new TokenResponse<>(response.asObject(Person.class));

    }

    /**
     * Gets persons.
     *
     * @param q     the q
     * @param token the token
     * @return the persons
     */
    public TokenResponse<Person[]> getPeople(String q, String token) {
        var response = httpManager.getList(PEOPLE, q, token);
        return new TokenResponse<>(response.asObject(Person[].class));
    }

    /**
     * Post person token response.
     *
     * @param body the body
     * @return the token response
     */
    public TokenResponse<Person> postPerson(Person person, String token) {
        var response = httpManager.post(PEOPLE, person, token);
        return new TokenResponse<>(response.asObject(Person.class));
    }
}
