package dk.creditoro.client.model;

import dk.creditoro.client.core.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type User model test.
 */
class UserModelTest {
    private final IUserModel model;

    /**
     * Instantiates a new User model test.
     */
    public UserModelTest() {
        var modelFactory = new ModelFactory();
        model = modelFactory.getUserModel();
    }

    /**
     * Login.
     */
    @Test
    void login() {
        var token = model.login("string@string.dk", "string");
        assertNotEquals(null, token);
    }
}