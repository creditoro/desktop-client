package dk.creditoro.client.networking.dummy_client;

import dk.creditoro.client.model.crud.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DummyClientTest
 */
class DummyClientTest {
    DummyClient dummyClient;

    public DummyClientTest() {
        dummyClient = new DummyClient();
    }

    @Test
    void login() {
        assertNull(dummyClient.login("someUser", "somePassword"));
    }

    @Test
    void register() {
        assertDoesNotThrow(() -> dummyClient.register(new User("10-10-10", "50505050", "string@string.dk",
                "MyName", "My Admin Role")));
    }

    @Test
    void searchChannels() {
        assertNotNull(dummyClient.searchChannels(""));
    }

    @Test
    void searchProductions() {
        assertNotNull(dummyClient.searchProductions(""));
    }

}
