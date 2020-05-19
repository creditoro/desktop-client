package dk.creditoro.client.view.frontpage;

import dk.creditoro.client.model.channel.IChannelModel;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.production.IProductionModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.logging.Logger;

public class FrontpageViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final StringProperty queryParam = new SimpleStringProperty();
    private final IChannelModel channelModel;
    private final IProductionModel productionModel;

    private final ObservableList<Channel> channelsList = FXCollections.observableArrayList();
    private final ObservableList<Production> productionsList = FXCollections.observableArrayList();
    private final ListProperty<Channel> listProperty = new SimpleListProperty<>(channelsList);
    private final ListProperty<Production> listProperty1 = new SimpleListProperty<>(productionsList);


    public FrontpageViewModel(IChannelModel channelModel, IProductionModel productionModel) {
        this.channelModel = channelModel;
        this.productionModel = productionModel;
        channelModel.addListener("kek", (this::onSearchChannelsResult));
        productionModel.addListener("kek", (this::onSearchProductionsResult));
    }

    private void onSearchChannelsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search channels result called.");
        var channels = (Channel[]) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            listProperty.clear();
            listProperty.addAll(channels);
        });

    }    private void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search channels result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            listProperty1.clear();
            listProperty1.addAll(productions);
        });
    }

    public StringProperty queryParamProperty() {
        return queryParam;
    }

    public void search() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        channelModel.search(q);
        productionModel.search(q);
        changeView();
    }

    public void changeView() {
        var q = queryParam.get();
        System.out.println("changing: " + q);
    }
}
