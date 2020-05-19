package dk.creditoro.client.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* EventNames
*/
class EventNamesTest {

	@Test
	void checkEnumes(){
		String message = "Check EventNames enumes";
		assertEquals("LOGIN_RESULT", EventNames.LOGIN_RESULT.toString(), 
				message);
		assertEquals("ON_SEARCH_CHANNELS_RESULT", 
				EventNames.ON_SEARCH_CHANNELS_RESULT.toString(), 
				message);
		assertEquals("ON_SEARCH_PRODUCTIONS_RESULT", 
				EventNames.ON_SEARCH_PRODUCTIONS_RESULT.toString(),
				message);
	}
	
}
