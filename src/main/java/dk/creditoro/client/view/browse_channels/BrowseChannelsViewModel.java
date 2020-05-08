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
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

import java.beans.PropertyChangeEvent;
import java.util.Comparator;
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
    private char currentCharacter;


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
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        channelModel.search(q);
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

    public ObservableList<Node> sortedList(TilePane tilePane) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(tilePane.getChildren());
        workingCollection.sort(Comparator.comparing(o -> channelName(o.getId())));
        return workingCollection;
    }


    public String channelName(String identifier) {
        for (Channel channel : listProperty) {
            if (channel.getIdentifier().equals(identifier)) {
                LOGGER.info(channel.getName());
                return channel.getName();
            }
        }
        return "";
    }

    public ObservableList<Node> sortedByCharacter(ObservableList<Node> list, ActionEvent actionEvent, HBox alphabet) {
        char character = 0;
        var i = 65;
        ObservableList<Node> observableList = FXCollections.observableArrayList(list);
        for (Node node : alphabet.getChildren()) {
            if (node == actionEvent.getSource()) {
                character = (char) i;
                Button button = (Button) actionEvent.getSource();
                var styleClass = button.getStyleClass();
                if (currentCharacter == character) {
                    styleClass.remove("bold");
                    currentCharacter = 0;
                    return observableList;
                } else {
                    currentCharacter = character;
                    styleClass.add("bold");
                }
            } else {
                Button button = (Button) node;
                var styleClass = button.getStyleClass();
                styleClass.remove("bold");
            }
            i++;
        }
        char finalCharacter = character;
        if (character != 0) {
            observableList.removeIf(node -> !channelName(node.getId()).toUpperCase().startsWith(String.valueOf(finalCharacter)));
        }
        return observableList;
    }
}
