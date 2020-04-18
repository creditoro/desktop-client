package dk.creditoro.client.model.login;

import dk.creditoro.client.core.ModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginModelTest {
    private final ILoginModel loginModel;

    /**
     * Instantiates a new User model test.
     */
    public LoginModelTest() {
        var modelFactory = new ModelFactory();
        loginModel = modelFactory.getLoginModel();
    }

    /**
     * Login.
     */
    @Test
    void login() {
        var loggedIn = loginModel.validateLogin("string@string.dk", "string");
        assertTrue(loggedIn);
    }
}