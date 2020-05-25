package dk.creditoro.client.view.browse_channel_productions;


import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.production.IProductionModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.util.*;
import java.util.logging.Logger;

/**
 * The type Browse channels view model.
 */
public class BrowseChannelProductionsViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final IProductionModel productionModel;
    private final ViewModelFactory viewModelFactory;
    private final ObservableList<Production> productionsList = FXCollections.observableArrayList();
    private final ListProperty<Production> listProperty = new SimpleListProperty<>(productionsList);
    private final Map<String, List<Production>> productionMap = new HashMap<>();
    private StringProperty queryParam = new SimpleStringProperty();
    private SimpleStringProperty channelName = new SimpleStringProperty();


    /**
     * Instantiates a new Login view model.
     *
     * @param productionModel  the channel model
     * @param viewModelFactory the view model factory
     */
    public BrowseChannelProductionsViewModel(IProductionModel productionModel, ViewModelFactory viewModelFactory) {
        this.productionModel = productionModel;
        this.productionModel.addListener("kek", (this::onSearchProductionsResult));
        this.viewModelFactory = viewModelFactory;
        queryParamProperty().setValue(null);
        search();
    }

    /**
     * Gets channel name.
     *
     * @return the channel name
     */
    public SimpleStringProperty getChannelName() {
        return channelName;
    }

    /**
     * Sets channel name.
     *
     * @param channelName the channel name
     */
    public void setChannelName(String channelName) {
        this.channelName.setValue(channelName);
    }

    /**
     * Create production map, with channelName as key and List of productions for that channel as value.
     *
     */
    public void createProductionMap() {
        LOGGER.info("Creating productionMap");
        for (Channel c : viewModelFactory.getBrowseChannelsViewModel().listPropertyProperty()) {
            productionMap.put(c.getName(), prodList(c.getName()));
        }
    }

    /**
     * Generate a List with all productions for a given channelName.
     *
     * @param chanName the chan name
     * @return the list
     */
    public List<Production> prodList(String chanName) {
        List<Production> pl = new ArrayList<>();
        for (Production p : listProperty) {
            if (p.getChannel().getName().equals(chanName)) {
                pl.add(p);
            }
        }
        return pl;
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
        LOGGER.info("On search channelPrograms result called.");
        var productions = (Production[]) propertyChangeEvent.getNewValue();
        listProperty.clear();
        listProperty.addAll(productions);
    }

    /**
     * Q search list.
     *
     * @return the list
     */
    public List<Production> qSearch() {
        List<Production> productions = new ArrayList<>(productionMap.get(channelName.getValue()));
        if (queryParamProperty().getValue() != null) {
            productions.removeIf(n -> (!n.getTitle().toLowerCase().contains(queryParam.getValue().toLowerCase())));
        }
        return productions;
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

