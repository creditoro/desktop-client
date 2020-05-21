package dk.creditoro.client.networking.dummy_client;

import dk.creditoro.client.model.crud.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

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

	@Test 
	void getCredits(){
		assertNotNull(dummyClient.getCredits(""));
	}

	@Test 
	void patchCredits(){
		assertNotNull(dummyClient.patchCredits("/helloID/", Map.of("jobtype", "MyMan", "User", new Object())));
	}

	@Test
	void postCredits(){
		assertNotNull(dummyClient.postCredits(new Credit("ID-1-1-1-11", 
				new Production("ID-3-3-3", "title-10", "Description LONG", 
					new User("ID-5-5-5", "30303030" , "Mail@Mail2.gg", "ProducerName", "admin"),
					new Channel("id-4-4-4", "DR1", "https://api.creditoro.nymann.dev")), 
				new Person("ID-2-2-2", "505055050", "Mail@mail.gg" , "MYNAMEISNOTHING"), 
				"jobtype")));
	}

}
