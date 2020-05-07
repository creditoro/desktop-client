package dk.creditoro.client.networking.rest_client.endpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.RestClient;
import kong.unirest.json.JSONObject;

/**
* ProductionsEndpointTest
*/
public class ProductionsEndpointTest {
	ProductionsEndpoint productionsEndpoint;
	IClient client;
	String token;
	Production[] productions;
	
	public ProductionsEndpointTest(){
		productionsEndpoint = new ProductionsEndpoint(new HttpManager());
		client = new RestClient();
		token = client.login("string@string.dk", "string");
	}

	@BeforeEach @Test
	void getProductions(){
		productions = productionsEndpoint.getProductions("", token);
		assertNotNull(productions);
	}

	//@Test
	void getProduction(){
				assertNull(productionsEndpoint.getProduction(productions[0]
					.getIdentifier()).getIdentifier(),
				"Check if we can get the same productions");
	}

	void getTheSameProduction(){
		assertEquals(productions[0].getIdentifier(), 
				productionsEndpoint.getProduction(productions[0]
					.getIdentifier()).getIdentifier(),
				"Check if we can get the same productions");
	}

	@Test
	void putProduction(){
		assertNull(productionsEndpoint.putProduction("", null), 
				"Fix this then the putProduction works");
	}

	@Test
	void patchProduction(){
		assertNull(productionsEndpoint.patchProduction("", null), 
				"Fix this then the patchProduction works");
	}

	@Test
	void postProductionWithToken(){
        var body = new JSONObject(Map.of("title", "Ringes Herrer", "producer_id", "pro10-10", "channel_id", "chan10-10"));
		assertNotNull(productionsEndpoint.postProduction(body, token), 
				"Fix this then productions works in the API");
	}
	@Test
	void postProduction(){
        var body = new JSONObject(Map.of("title", "Ringes Herrer", "producer_id", "pro10-10", "channel_id", "chan10-10"));
		assertNull(productionsEndpoint.postProduction(body), 
				"Fix this then productions works in the API");
	}
}
