package dk.creditoro.client.networking.rest_client.endpoints;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.*;

import dk.creditoro.client.model.crud.Channel;
import kong.unirest.json.JSONObject;

/**
* HttpManagerTest
*/
public class HttpManagerTest {
	HttpManager httpManager;
	String token;

	public HttpManagerTest(){
		httpManager = new HttpManager();
	}
	
	@BeforeEach @Test
	void login(){
		JSONObject body = new JSONObject(Map.of("email", "string@string.dk", "password", "string"));
		System.out.println(body.get("email") + " h " + body.getString("password"));
		token = httpManager.post("/users/login", body).asJson().getBody().getObject().getString("token");
		assertNotNull(token);
	}

	@Test
	void getObjects(){
		var response = httpManager.getList("/channels/" , "", "");
		var channels = response.asObject(Channel[].class).getBody();
		for (Channel channel : channels) {
			System.out.println(channel);	
		}
		assertNotNull(channels[0].getName());	
	}
}
