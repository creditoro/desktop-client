package dk.creditoro.client.view.browse_productions;

import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.production.IProductionModel;
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
     */
    public BrowseProductionsViewModel(IProductionModel productionModel, IUserModel userModel) {
        this.productionModel = productionModel;
        this.userModel = userModel;
        this.productionModel.addListener("kek", (this::onSearchProductionsResult));
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
        LOGGER.info("On search productions result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            listProperty.clear();
            listProperty.addAll(productions);
        });
    }

    public ListProperty<Production> listPropertyProperty() {
        return listProperty;
    }

    public ObservableList<Node> sortedList(TilePane tilePane) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(tilePane.getChildren());
        workingCollection.sort((o1, o2) -> {
            try {
                return productionName(o1.getId()).compareTo(productionName(o2.getId()));
            } catch (NullPointerException ex) {
                LOGGER.info("Production does not exist");
            }
            return 0;
        });
        return workingCollection;
    }

    public String productionName(String identifier) {
        for (Production production : listProperty) {
            if (production.getIdentifier().equals(identifier)) {
                LOGGER.info(production.getTitle());
                return production.getTitle();
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
            observableList.removeIf(node -> !productionName(node.getId()).toUpperCase().startsWith(String.valueOf(finalCharacter)));
        }
        return observableList;
    }
}
