package dk.creditoro.client.model.production;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import dk.creditoro.client.model.crud.User;
import org.junit.jupiter.api.Test;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.networking.IClient;

/**
* ProductionModelTest
*/
public class ProductionModelTest {
	ProductionModel productionModel;
	IClient client;
	User currentUser;
	
	public ProductionModelTest(){
		client = new ClientFactory().getRestClient();
		productionModel = new ProductionModel(client);
		currentUser = client.login("string@string.dk", "string");
	}

	@Test void search(){
	assertDoesNotThrow(()-> productionModel.search(""));
	}
}
