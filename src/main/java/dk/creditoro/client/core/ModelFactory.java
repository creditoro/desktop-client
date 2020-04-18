package dk.creditoro.client.core;

import dk.creditoro.client.model.UserModel;
import dk.creditoro.client.model.IUserModel;

/**
 * The type Model factory is responsible for creating and providing the Data Model classes.
 * Pattern: Factory method - https://en.wikipedia.org/wiki/Factory_method_pattern
 */
public class ModelFactory {
    private IUserModel userModel;

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
}
