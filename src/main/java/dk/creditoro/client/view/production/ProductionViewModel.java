package dk.creditoro.client.view.production;

import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.user.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import java.beans.PropertyChangeEvent;
import java.util.logging.Logger;

public class ProductionViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ICreditModel creditModel;
    private final IUserModel userModel;

    private final StringProperty queryParam = new SimpleStringProperty();
    private final ObservableList<Credit> creditList = FXCollections.observableArrayList();
    private final ListProperty<Credit> listProperty = new SimpleListProperty<>(creditList);

    public ProductionViewModel(ICreditModel creditModel, IUserModel userModel)
    {
        this.creditModel = creditModel;
        this.userModel = userModel;
        this.creditModel.addListener("kek", (this::onSearchProductionsResult));
    }

    public StringProperty queryParamProperty() {
        return queryParam;
    }

    public void search() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s'", q);
        LOGGER.info(message);
        creditModel.search(q);
    }

    private void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search productions result called.");
        var credits = (Credit[]) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            listProperty.clear();
            listProperty.addAll(credits);
        });
    }

    public ListProperty<Credit> listPropertyProperty() {
        return listProperty;
    }


    //******** NEEDS TO BE UPDATED ********
    public ObservableList<Node> sortedList(TilePane tilePane) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(tilePane.getChildren());
        workingCollection.sort((o1, o2) -> {
            try {
                return 1;
                //return productionTitle(o1.getId()).compareTo(productionTitle(o2.getId()));
            } catch (NullPointerException ex) {
                LOGGER.info("Production does not exist");
            }
            return 0;
        });
        return workingCollection;
    }

    /*
    public String productionTitle(String identifier) {
        for (Credit credit : listProperty) {
            if (credit.getIdentifier().equals(identifier)) {
                LOGGER.info(credit.getTitle());
                return credit.getTitle();
            }
        }
        return "";
    }*/
}
