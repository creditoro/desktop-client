package dk.creditoro.client.view.login_page;

import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.UserModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Login view model test.
 */
class LoginViewModelTest {
    private final LoginViewModel model;

    /**
     * Instantiates a new Login view model test.
     */
    public LoginViewModelTest() {
        var modelFactory = new ModelFactory();
        var viewModelFactory = new ViewModelFactory(modelFactory);
        model = viewModelFactory.getLoginViewModel();
    }

    /**
     * Sign in.
     */
    @Test
    void signIn() {
        model.signIn("string@string.dk", "string");
        assertNotEquals(null, model.getToken());
    }
}