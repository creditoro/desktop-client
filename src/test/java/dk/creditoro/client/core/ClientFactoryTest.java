package dk.creditoro.client.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


/**
* ClientFactoryTest
*/
public class ClientFactoryTest {
	ClientFactory clientFactory;
	public ClientFactoryTest(){
		clientFactory = new ClientFactory();
	}

	@Test
	public void notNullRestClient(){
		assertNotNull(clientFactory.getRestClient());	
	}
	@Test	
	public void getRestClient(){
		assertEquals(clientFactory.getRestClient(), clientFactory.getRestClient());	
	}

	@Test
	public void notNullDummyClient(){
		assertNotNull(clientFactory.getDummyClient());	
	}
	@Test	
	public void getDummyClient(){
		assertEquals(clientFactory.getDummyClient(), clientFactory.getDummyClient());	
	}
}
