package dk.creditoro.client.model.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* UserTest
*/
public class UserTest {
	String message = "Something ind Users.java changed?";
	User user, user1;

	public UserTest(){
		user = new User("string@string.dk", "string");
		user1 = new User("10-10-10", "50505050", "string@string.dk", "string", 
				"MyName", "My Admin Role", "token10-10");
	}
	
	@Test void setToken(){
		user.setToken("newToken");
		assertEquals("newToken", user.getToken(), message);
	}


	@Test void testGetMethodForUsers(){
		assertEquals("10-10-10", user1.getIdentifier(), message);
		assertEquals("50505050", user1.getPhone(), message);
		assertEquals("string@string.dk", user1.getEmail(), message);
		assertEquals("string", user1.getPassword(), message);
		assertEquals("MyName", user1.getName(), message);
		assertEquals("My Admin Role", user1.getRole(), message);
		assertEquals("token10-10", user1.getToken(), message);
	}

	@Test void shouldNotBeSet(){
		assertEquals("", user.getIdentifier(), message);
		assertEquals("", user.getPhone(), message);
		assertEquals("string@string.dk", user.getEmail(), message);
		assertEquals("string", user.getPassword(), message);
		assertEquals("", user.getName(), message);
		assertEquals("", user.getRole(), message);
	}

}
