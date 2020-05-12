package dk.creditoro.client.model.production;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.model.crud.User;
import dk.creditoro.client.networking.IClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * ProductionModelTest
 */
public class ProductionModelTest {
    ProductionModel productionModel;
    IClient client;
    User currentUser;

    public ProductionModelTest() {
        client = new ClientFactory().getRestClient();
        productionModel = new ProductionModel(client);
        currentUser = client.login("string@string.dk", "string");
    }

    @Test
    void search() {
        assertDoesNotThrow(() -> productionModel.search(""));
    }
}
