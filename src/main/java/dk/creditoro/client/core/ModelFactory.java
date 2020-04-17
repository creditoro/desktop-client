package dk.creditoro.client.core;

import dk.creditoro.client.model.User;
import dk.creditoro.client.model.IUser;

/**
 * The type Model factory.
 */
public class ModelFactory {
    private IUser model;

    /**
     * Gets model.
     *
     * @return the model
     */
    public IUser getModel() {
        if(model == null) {
            model = new User();
        }
        return model;
    }
}
