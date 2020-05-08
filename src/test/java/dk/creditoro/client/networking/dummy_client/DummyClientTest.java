package dk.creditoro.client.networking.dummy_client;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import dk.creditoro.client.model.crud.User;

/**
* DummyClientTest
*/
public class DummyClientTest {
	DummyClient dummyClient;

	public DummyClientTest(){
		dummyClient = new DummyClient();
	}

	@Test void login(){
		assertNull(dummyClient.login("someUser", "somePassword"));
	}
	
	@Test void register(){
		assertDoesNotThrow(()-> dummyClient.register(new User("someMail", "somePassword")));
	}

	@Test void searchChannels(){
		assertNotNull(dummyClient.searchChannels("", "token"));
	}
	
	@Test void searchProductions(){
		assertNotNull(dummyClient.searchProductions("", "token"));
	}

}
