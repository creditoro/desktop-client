package dk.creditoro.client.networking.rest_client.endpoints;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.*;

import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.RestClient;
import kong.unirest.json.JSONObject;

/**
* ChannelsEndpointTest
*/
public class ChannelsEndpointTest {
	ChannelsEndpoint channelsEndpoint;
	Channel[] channels;

	public ChannelsEndpointTest(){
		channelsEndpoint = new ChannelsEndpoint(new HttpManager());
	}

	@BeforeEach @Test
	void getChannels(){
		channels = channelsEndpoint.getChannels("", "");
		assertTrue(channels.length != 0);
		assertNotEquals(channels[0].getName(), channels[1].getName(),
				"Makes sure it does not resive 2 of the same channels");
	}

	@Test
	void getChannel(){
		assertNull(channelsEndpoint.getChannel(channels[0]
					.getIdentifier()), 
				"Could not get the same channel Identifier?");
	}

	@Test 
	void putChannel(){
        var body = new JSONObject(Map.of("name", "DR1", "icon_url", "someWhere"));
		assertNull(channelsEndpoint.putChannel(channels[0].getIdentifier(), body), 
					"Remeber to fix me then you implement putChannel");
	}

	@Test
	void patchChannel(){
		assertNull(channelsEndpoint.patchChannel(channels[0].getIdentifier(), 
					Map.of("Hello", new Object())), 
					"Remeber to fix me then you implement patchChannel");
	}

	@Test 
	void postChannel(){
		//Make login
		IClient client = new RestClient();
		var token = client.login("string@string.dk", "string");
		//Add a channel that already exsiset
        var body = new JSONObject(Map.of("name", "DR1", "icon_url", "someWhere"));
		assertTrue(channelsEndpoint.postChannel(body, token).getName() == null, 
					"It can't post the same channel, and you say you don't have DR1?!?!?! " + 
					"Wich country do you live ind?");

		//Test a channel that is not in the database
        body = new JSONObject(Map.of("name", "99999", "icon_url", "someWhere"));
		var channelResponse = channelsEndpoint.postChannel(body, token);
		assertNotNull(channelResponse, 
					"For somereason it could not post the channe 99999 to the API?");
		//Delete the channel we just made
		assertTrue(channelsEndpoint.deleteChannel(channelResponse.getIdentifier(), token));



	}

	@Test void deleteChannel(){
		IClient client = new RestClient();
		var token = client.login("string@string.dk", "string");
		// Deletes the channel on the server
		assertTrue(channelsEndpoint.deleteChannel(channels[0].getIdentifier(), token));

		// Add the channel back to the server
        var body = new JSONObject(Map.of("name", channels[0].getName(), 
					"icon_url", channels[0].getIconUrl()));
		channelsEndpoint.postChannel(body, token);
	}
	
}
