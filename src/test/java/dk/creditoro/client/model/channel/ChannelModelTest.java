package dk.creditoro.client.model.channel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.networking.IClient;

/**
* ChannelModelTest
*/
public class ChannelModelTest {
	IChannelModel channelModel;
	IClient client;
	
	public ChannelModelTest(){
		client = new ClientFactory().getRestClient();
		channelModel = new ChannelModel(client);
	}

	@Test void search(){
	assertDoesNotThrow(() -> 
			channelModel.search("DR1", client.login("string@string.dk", "string"))
			);
	}
}
