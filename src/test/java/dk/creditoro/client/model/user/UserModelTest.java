package dk.creditoro.client.model.user;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.model.crud.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * UserModelTest
 */
class UserModelTest {
    UserModel userModel;
    String password = "string";

    public UserModelTest() {
        userModel = new UserModel(new ClientFactory().getRestClient());
        assertDoesNotThrow(() -> userModel.login("string@string.dk", password));
    }

    @Test
    void getCurrentUser() {
        assertNotNull(userModel.getCurrentUser(),
                "Should be able to get the new user from login");
    }

    @Test
    void register() {
        var testUser = new User("+45 88 88 88 88", "peter@leasy.dk", "Peter", "Chef");
        assertDoesNotThrow(() -> userModel.register(testUser, "string"),
                "Updated this test after you implement register USER ;) ");
    }

}
