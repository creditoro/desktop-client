package dk.creditoro.client.view.channel_programs;


import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.production.IProductionModel;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.view.shared_viewmodel_func.FindCharacter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

import java.beans.PropertyChangeEvent;
import java.util.*;
import java.util.logging.Logger;

/**
 * The type Browse channels view model.
 */
public class ChannelProgramsViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IProductionModel productionModel;
    private final IUserModel userModel;
    private final ViewModelFactory viewModelFactory;
    private final ObservableList<Production> productionsList = FXCollections.observableArrayList();
    private final ListProperty<Production> listProperty = new SimpleListProperty<>(productionsList);
    private final Map<String, List<Production>> productionMap = new HashMap<>();
    private StringProperty queryParam = new SimpleStringProperty();
    private char currentCharacter;
    private String id;

    /**
     * Instantiates a new Login view model.
     *
     * @param productionModel the channel model
     */
    public ChannelProgramsViewModel(IProductionModel productionModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
        this.productionModel = productionModel;
        this.userModel = userModel;
        this.productionModel.addListener("kek", (this::onSearchProductionsResult));
        this.viewModelFactory = viewModelFactory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, List<Production>> createProductionMap() {
        LOGGER.info("Creating productionMap");
        for (Channel c : viewModelFactory.getBrowseChannelsViewModel().listPropertyProperty()) {
            productionMap.put(c.getName(), prodList(c.getName()));
        }
        return productionMap;
    }

    public List<Production> prodList(String chanName) {
        List<Production> pl = new ArrayList<>();
        for (Production p : listProperty) {
            if (p.getChannel().getName().equals(chanName)) {
                pl.add(p);
            }
        }
        return pl;
    }

    public StringProperty queryParamProperty() {
        return queryParam;
    }

    public void search() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        productionModel.search(q);
    }

    private void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search channelPrograms result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        listProperty.clear();
        listProperty.addAll(productions);
    }

    public ListProperty<Production> listPropertyProperty() {
        return listProperty;
    }


    public ObservableList<Node> sortedChannelList(TilePane tilePane, String sortingMethod) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(tilePane.getChildren());
        Comparator<Node> comparator = Comparator.comparing(this::productionTitle);
        if (sortingMethod.equals("Å-A")) {
            workingCollection.sort(comparator.reversed());
            return workingCollection;
        }
        workingCollection.sort(comparator);
        return workingCollection;
    }

    public String productionTitle(Node node) {
        var identifier = node.getId();
        for (int i = 0; i < listProperty.getSize(); i++) {
            Production production = listProperty.get(i);
            if (production.getIdentifier().equals(identifier)) {
                return production.getTitle();
            }
        }
        return "";
    }

    public ObservableList<Node> sortedByCharacter(ObservableList<Node> list, ActionEvent actionEvent, HBox alphabet) {
        FindCharacter findCharacter = new FindCharacter();
        ObservableList<Node> observableList = FXCollections.observableArrayList(list);
        char character = findCharacter.getCharacter(actionEvent, alphabet, currentCharacter);
        currentCharacter = character;

        if (character != 0) {
            observableList.removeIf(node -> !productionTitle(node).toUpperCase().startsWith(String.valueOf(character)));
        } else {
            return observableList;
        }
        return observableList;
    }
}

