package dk.creditoro.client.core;

import dk.creditoro.client.model.login.ILoginModel;
import dk.creditoro.client.model.login.LoginModel;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.model.user.UserModel;

/**
 * The type Model factory is responsible for creating and providing the Data Model classes.
 * Pattern: Factory method - https://en.wikipedia.org/wiki/Factory_method_pattern
 */
public class ModelFactory {
    private IUserModel userModel;
    private ILoginModel loginModel;

    /**
     * Gets model.
     *
     * @return the model
     */
    public IUserModel getUserModel() {
        if (userModel == null) {
            userModel = new UserModel();
        }
        return userModel;
    }

    /**
     * Gets login model.
     *
     * @return the login model
     */
    public ILoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModel();
        }
        return loginModel;
    }
}
