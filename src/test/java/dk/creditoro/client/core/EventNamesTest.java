package dk.creditoro.client.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* EventNames
*/
public class EventNamesTest {

	@Test
	public void checkEnumes(){
		String message = "Check EventNames enumes";
		assertEquals(EventNames.LOGIN_RESULT.toString(),
				"LOGIN_RESULT", message);
		assertEquals(EventNames.ON_SEARCH_CHANNELS_RESULT.toString(), 
				"ON_SEARCH_CHANNELS_RESULT", message);
		assertEquals(EventNames.ON_SEARCH_PRODUCTIONS_RESULT.toString(),
				"ON_SEARCH_PRODUCTIONS_RESULT", message);
	}
	
}
