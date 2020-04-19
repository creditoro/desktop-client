package dk.creditoro.client.core;

import dk.creditoro.client.model.channel.ChannelModel;
import dk.creditoro.client.model.channel.IChannelModel;
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
            userModel = new UserModel(clientFactory.getClient());
        }
        return userModel;
    }

    public IChannelModel getChannelModel() {
        if (channelModel == null) {
            channelModel = new ChannelModel(clientFactory.getClient());
        }
        return channelModel;
    }
}
