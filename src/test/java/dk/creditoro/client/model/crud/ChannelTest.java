package dk.creditoro.client.model.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* ChannelTest
*/
class ChannelTest {
	Channel channel;

	public ChannelTest(){
		channel = new Channel("10-10-10", "DR1", "tvtid.dk/dr1.png");
	}

	@Test void getIdentifier(){
		assertEquals("10-10-10", channel.getIdentifier(),
				"Channel Identifier changed?");
	}
	
	@Test void getName(){
		assertEquals("DR1", channel.getName(),
				"Channel Name changed?");
	}
	
	@Test void getIconUrl(){
		assertEquals("tvtid.dk/dr1.png", channel.getIconUrl(),
				"Channel getIconUrl changed?");
	}

	@Test void channelToString(){
		assertEquals(String.format("Channel (name=%s)", "DR1"), channel.toString(),
				"Channel toString Method changed?");
	}
}
