package dk.creditoro.client.networking.rest_client.endpoints;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersEndpointTest {
    UsersEndpoint usersEndpoint;
    public UsersEndpointTest() {
        usersEndpoint = new UsersEndpoint(new HttpManager());
    }

    @Test
    void getUser() {
    }

    @Test
    void putUser() {
    }

    @Test
    void patchUser() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void postUser() {
    }

    @Test
    void postLogin() {
        var token = usersEndpoint.postLogin("string@string.dk", "string");
        assertNotNull(token);
        token = usersEndpoint.postLogin("wronglogin@string.dk", "string");
        assertNull(token);
    }
}
