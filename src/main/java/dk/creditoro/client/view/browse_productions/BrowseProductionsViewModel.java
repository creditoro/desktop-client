package dk.creditoro.client.view.browse_productions;

import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.production.IProductionModel;
import dk.creditoro.client.model.user.IUserModel;
import dk.creditoro.client.view.shared_viewmodel_func.SharedViewModelFunc;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

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
    private Button btnAccount;
    private final SharedViewModelFunc sharedViewModelFunc = new SharedViewModelFunc();

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

    public void setBtnAccount(Button btnAccount) {
        this.btnAccount = btnAccount;
    }

    public void setMail() {
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
        productionModel.search(q);
    }

    private void onSearchProductionsResult(PropertyChangeEvent propertyChangeEvent) {
        LOGGER.info("On search productions result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        listProperty.clear();
        listProperty.addAll(productions);
    }

    /**
     * List property property list property.
     *
     * @return the list property
     */
    public ListProperty<Production> listPropertyProperty() {
        return listProperty;
    }

}
