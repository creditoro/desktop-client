package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.model.channel.IChannelModel;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.view.shared_viewmodel_func.SharedViewModelFunc;
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
import org.apache.xmlgraphics.java2d.ps.PSTextHandler;

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
    private final SharedViewModelFunc sharedViewModelFunc = new SharedViewModelFunc();
    private IUserModel userModel;
    private Button btnAccount;

    private final ObservableList<Channel> channelsList = FXCollections.observableArrayList();
    private final ListProperty<Channel> listProperty = new SimpleListProperty<>(channelsList);
    private char currentCharacter;

    /**
     * Instantiates a new Login view model.
     *
     * @param channelModel the channel model
     * @param userModel    the user model
     */
    public BrowseChannelsViewModel(IChannelModel channelModel, IUserModel userModel) {
        this.userModel = userModel;
        this.channelModel = channelModel;
        this.channelModel.addListener("kek", (this::onSearchChannelsResult));
    }

    public void setBtnAccount(Button btnAccount) {
        this.btnAccount = btnAccount;
    }

    public void setMail(){
        sharedViewModelFunc.setUserEmail(btnAccount,userModel);
    }

    /**
     * Query param property string property.
     *
     * @return the string property
     */
    public StringProperty queryParamProperty() {
        return queryParam;
    }

    /**
     * Search.
     */
    public void search() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        channelModel.search(q);
    }

    private void onSearchChannelsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search channels result called.");
        var channels = (Channel[]) propertyChangeEvent.getNewValue();
        listProperty.clear();
        listProperty.addAll(channels);
    }

    /**
     * List property property list property.
     *
     * @return the list property
     */
    public ListProperty<Channel> listPropertyProperty() {
        return listProperty;
    }

    /**
     * Sorted channel list observable list.
     *
     * @param tilePane      the tile pane
     * @param sortingMethod the sorting method
     * @return the observable list
     */
    public ObservableList<Node> sortedChannelList(TilePane tilePane, String sortingMethod) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(tilePane.getChildren());
        Comparator<Node> comparator = Comparator.comparing(this::channelName);
        if (sortingMethod.equals("Å-A")) {
            workingCollection.sort(comparator.reversed());
            return workingCollection;
        }
        workingCollection.sort(comparator);
        return workingCollection;
    }

    /**
     * Channel name string.
     *
     * @param node the node
     * @return the string
     */
    public String channelName(Node node) {
        var identifier = node.getId();
        for (int i = 0; i < listProperty.getSize(); i++) {
            Channel channel = listProperty.get(i);
            if (channel.getIdentifier().equals(identifier)) {
                return channel.getName();
            }
        }
        return "";
    }


    /**
     * Sorted by character observable list.
     *
     * @param list        the list
     * @param actionEvent the action event
     * @param alphabet    the alphabet
     * @return the observable list
     */
    public ObservableList<Node> sortedByCharacter(ObservableList<Node> list, ActionEvent actionEvent, HBox alphabet) {
        currentCharacter = new SharedViewModelFunc().getCharacter(actionEvent, alphabet, currentCharacter);
        ObservableList<Node> observableList = FXCollections.observableArrayList(list);

        if (currentCharacter == 0) {
            return observableList;
        } else {
            observableList.removeIf(node -> !channelName(node).toUpperCase().startsWith(String.valueOf(currentCharacter)));
            return observableList;
        }
    }
}
