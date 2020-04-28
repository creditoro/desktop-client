package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.model.channel.IChannelModel;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.user.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.beans.PropertyChangeEvent;
import java.util.logging.Logger;

/**
 * The type Browse channels view model.
 */
public class BrowseChannelsViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final StringProperty queryParam = new SimpleStringProperty();
    private final IChannelModel channelModel;
    private final IUserModel userModel;

    private final ObservableList<Channel> channelsList = FXCollections.observableArrayList();
    private final ListProperty<Channel> listProperty = new SimpleListProperty<>(channelsList);


    /**
     * Instantiates a new Login view model.
     *
     * @param channelModel the channel model
     */
    public BrowseChannelsViewModel(IChannelModel channelModel, IUserModel userModel) {
        this.channelModel = channelModel;
        this.userModel = userModel;
        this.channelModel.addListener("kek", (this::onSearchChannelsResult));
    }

    public StringProperty queryParamProperty() {
        return queryParam;
    }

    public void search() {
        var token = userModel.getToken();
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s', token is: '%s.'", q, token);
        LOGGER.info(message);
        channelModel.search(q, token);
    }

    private void onSearchChannelsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search channels result called.");
        var channels = (Channel[]) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            listProperty.clear();
            listProperty.addAll(channels);
        });
    }

    public ListProperty<Channel> listPropertyProperty() {
        return listProperty;
    }
}
