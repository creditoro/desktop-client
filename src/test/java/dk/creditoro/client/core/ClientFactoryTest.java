package dk.creditoro.client.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


/**
* ClientFactoryTest
*/
class ClientFactoryTest {
	ClientFactory clientFactory;
	public ClientFactoryTest(){
		clientFactory = new ClientFactory();
	}

	@Test
	void notNullRestClient(){
		assertNotNull(clientFactory.getRestClient());	
	}
	@Test	
	void getRestClient(){
		assertEquals(clientFactory.getRestClient(), clientFactory.getRestClient());	
	}

	@Test
	void notNullDummyClient(){
		assertNotNull(clientFactory.getDummyClient());	
	}
	@Test	
	void getDummyClient(){
		assertEquals(clientFactory.getDummyClient(), clientFactory.getDummyClient());	
	}
}
