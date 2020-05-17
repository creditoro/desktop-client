package dk.creditoro.client.view.browse_productions;

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
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * The type Browse productions view model.
 */
public class BrowseProductionsViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final StringProperty queryParam = new SimpleStringProperty();
    private final IProductionModel productionModel;
    private final IUserModel userModel;

    private final ObservableList<Production> productionsList = FXCollections.observableArrayList();
    private final ListProperty<Production> listProperty = new SimpleListProperty<>(productionsList);
    private char currentCharacter;

    /**
     * Instantiates a new Login view model.
     *
     * @param productionModel the channel model
     * @param userModel       the user model
     */
    public BrowseProductionsViewModel(IProductionModel productionModel, IUserModel userModel) {
        this.productionModel = productionModel;
        this.userModel = userModel;
        this.productionModel.addListener("kek", (this::onSearchProductionsResult));
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
        productionModel.search(q);
    }

    private void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search productions result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            listProperty.clear();
            listProperty.addAll(productions);
        });
    }

    /**
     * List property property list property.
     *
     * @return the list property
     */
    public ListProperty<Production> listPropertyProperty() {
        return listProperty;
    }

    /**
     * Sorted list observable list.
     *
     * @param tilePane the tile pane
     * @return the observable list
     */
    public ObservableList<Node> sortedList(TilePane tilePane) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(tilePane.getChildren());
        workingCollection.sort(Comparator.comparing(this::productionTitle));
        return workingCollection;
    }

    /**
     * Production title string.
     *
     * @param node the node
     * @return the string
     */
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


    /**
     * Sorted by character observable list.
     *
     * @param list        the list
     * @param actionEvent the action event
     * @param alphabet    the alphabet
     * @return the observable list
     */
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
