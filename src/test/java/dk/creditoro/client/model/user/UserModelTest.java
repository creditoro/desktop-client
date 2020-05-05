package dk.creditoro.client.model.user;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.model.crud.User;

/**
* UserModelTest
*/
public class UserModelTest {
	UserModel userModel;
	String password = "string";
	
	public UserModelTest(){
		userModel = new UserModel(new ClientFactory().getRestClient());
	}

	@BeforeEach void loggin(){
		assertDoesNotThrow(()-> userModel.login("string@string.dk", password));
	}

	@Test void getCurrentUser(){
		assertNotNull(userModel.getCurrentUser(),
				"Should be able to get the new user aflogin");
	}

	@Test void getCurrentUserPassword(){
		assertEquals(password, userModel.getCurrentUser().getPassword(),
				"Could not get the current UserPassword");
	}

	@Test void getToken(){
		assertNotNull(userModel.getToken());
	}

	@Test void register(){
		assertDoesNotThrow(()-> userModel.register(new User("some@mail.dk", "somePassword")) ,
				"Updated this test after you implement register USER ;) ");
	}

}
