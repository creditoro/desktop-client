package dk.creditoro.client.core;

import dk.creditoro.client.model.channel.ChannelModel;
import dk.creditoro.client.model.channel.IChannelModel;
import dk.creditoro.client.model.credit.CreditModel;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.person.IPersonModel;
import dk.creditoro.client.model.person.PersonModel;
import dk.creditoro.client.model.production.IProductionModel;
import dk.creditoro.client.model.production.ProductionModel;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.model.user.UserModel;

/**
 * The type Model factory is responsible for creating and providing the Data Model classes.
 * Pattern: Factory method - https://en.wikipedia.org/wiki/Factory_method_pattern
 */
public class ModelFactory {
    private final ClientFactory clientFactory;
    private IUserModel userModel;
    private IChannelModel channelModel;
    private IProductionModel productionModel;
    private ICreditModel creditModel;
    private IPersonModel personModel;

    public ModelFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public IUserModel getUserModel() {
        if (userModel == null) {
            userModel = new UserModel(clientFactory.getRestClient());
        }
        return userModel;
    }

    public IChannelModel getChannelModel() {
        if (channelModel == null) {
            channelModel = new ChannelModel(clientFactory.getRestClient());
        }
        return channelModel;
    }

    public IProductionModel getProductionModel() {
        if (productionModel == null) {
            productionModel = new ProductionModel(clientFactory.getRestClient());
        }
        return productionModel;
    }

    public ICreditModel getCreditModel() {
        if (creditModel == null) {
            creditModel = new CreditModel(clientFactory.getRestClient());
        }
        return creditModel;
    }

    public IPersonModel getPersonModel() {
        if (personModel == null) {
            personModel = new PersonModel(clientFactory.getRestClient());
        }
        return personModel;
    }
}
