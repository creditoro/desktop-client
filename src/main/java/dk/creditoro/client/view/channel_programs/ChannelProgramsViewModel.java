package dk.creditoro.client.view.channel_programs;


import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.production.IProductionModel;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.view.shared_viewmodel_func.FindCharacter;
import javafx.application.Platform;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * The type Browse channels view model.
 */
public class ChannelProgramsViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private StringProperty queryParam = new SimpleStringProperty();
    private final IProductionModel productionModel;
    private final IUserModel userModel;
    private final ArrayList<Production> cachedProductions = new ArrayList<>();

    private final ObservableList<Production> productionsList = FXCollections.observableArrayList();
    private final ListProperty<Production> listProperty = new SimpleListProperty<>(productionsList);

    private char currentCharacter;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * Instantiates a new Login view model.
     *
     * @param productionModel the channel model
     */
    public ChannelProgramsViewModel(IProductionModel productionModel, IUserModel userModel) {
        this.productionModel = productionModel;
        this.userModel = userModel;
        this.productionModel.addListener("kek", (this::onSearchProductionsResult));
    }

    public StringProperty queryParamProperty() {
        return queryParam;
    }

    public void getProductions() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        listProperty.clear();
        productionModel.search(id);
    }

    public void search() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        productionModel.search(q);
    }

    public void filter() {
        listProperty.clear();
        for (int i = 0; i < cachedProductions.size(); i++) {
            Production p = cachedProductions.get(i);
            String name = p.getChannel().getName().toLowerCase();
            String q;
            if (queryParam.getValue().isEmpty()) {
                q = "";

            } else {
                q = queryParam.getValue().toLowerCase();
            }
            if (name.contains(q)) {
                listProperty.add(p);
            }
        }
    }


    private void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search channels result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            listProperty.clear();
            cachedProductions.clear();
            listProperty.addAll(productions);
            cachedProductions.addAll(Arrays.asList(productions));
        });
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

