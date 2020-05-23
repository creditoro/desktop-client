package dk.creditoro.client.view.browse_channel_productions;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.view.IViewController;
import dk.creditoro.client.view.shard_controller_func.SharedControllerFunc;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * The type Browse channel productions controller.
 */
public class BrowseChannelProductionsController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     * The Sorting list.
     */
    ObservableList<String> sortingList = FXCollections.observableArrayList("A-Å", "Å-A");
    private BrowseChannelProductionsViewModel browseChannelProductionsViewModel;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;
    private ObservableList<Node> productionsList;
    private Map<String, VBox> cachedProductions;
    private Map<String, List<Production>> cachedProductionMap;
    private SharedControllerFunc sharedControllerFunc;
    @FXML
    private ScrollPane productionPane;
    @FXML
    private TextField productionSearch;
    @FXML
    private HBox alphabet;
    @FXML
    private Button btnAccount;
    @FXML
    private TextField search;
    @FXML
    private ChoiceBox<String> choiceBox;

    /**
     * Handle search bar.
     */
    public void handleSearchBar() {
        LOGGER.info("Handle search bar.");
    }

    /**
     * Btn account.
     */
    public void btnAccount() {
        viewHandler.openView(Views.LOGIN);
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewModelFactory = viewModelFactory;
        browseChannelProductionsViewModel = viewModelFactory.getBrowseChannelProductionsViewModel();
        this.viewHandler = viewHandler;
        this.cachedProductions = new HashMap<>();
        sharedControllerFunc = new SharedControllerFunc();

        choiceBox.setValue("A-Å");
        choiceBox.setItems(sortingList);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> sorted(t1.intValue()));
        this.cachedProductionMap = viewModelFactory.getBrowseChannelProductionsViewModel().createProductionMap();

        //Add listener to channelSearch text area
        productionSearch.textProperty().bindBidirectional(browseChannelProductionsViewModel.queryParamProperty());
        browseChannelProductionsViewModel.getChannelName().addListener((observableValue, productions, newValue) -> updateList());
        updateList();
        btnAccount.setText("user.getEmail()");
    }

    private void doneLoading(TilePane tilePane) {
        Platform.runLater(() -> {
            productionPane.setContent(tilePane);
            productionsList = FXCollections.observableArrayList(tilePane.getChildren());
        });
    }

    private void updateList() {
        LOGGER.info("Update grid called. ChannelPrograms");

        // Create TilePane for productions
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(15, 0, 0, 0));
        tilePane.prefWidthProperty().bind(productionPane.widthProperty());
        List<Node> children = computeChildren(tilePane);
        Platform.runLater(() -> tilePane.getChildren().addAll(children));
        doneLoading(tilePane);
    }


    /**
     * Compute children list.
     *
     * @param tilePane the tile pane
     * @return the list
     */
    public List<Node> computeChildren(TilePane tilePane) {
        List<Node> list = new ArrayList<>();

        // Create VBox for each production and add title and description
        for (Production production : browseChannelProductionsViewModel.qSearch()) {
            sharedControllerFunc.generateChildren(tilePane, list, production, cachedProductions, productionPane, viewModelFactory, viewHandler);
        }
        return list;
    }

    /**
     * On search.
     */
    public void onSearch() {
        browseChannelProductionsViewModel.qSearch();
        updateList();
    }

    /**
     * Sort by character.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void sortByCharacter(ActionEvent actionEvent) {
        TilePane tp = (TilePane) productionPane.getContent();
        tp.getChildren().setAll(browseChannelProductionsViewModel.sortedByCharacter(productionsList, actionEvent, alphabet));
    }

    /**
     * Sorted.
     *
     * @param choice the choice
     */
    public void sorted(int choice) {
        TilePane tilePane = (TilePane) productionPane.getContent();
        String choiceString;
        if (choice == 1) {
            choiceString = "Å-A";
        } else {
            choiceString = "A-Å";
        }
        tilePane.getChildren().setAll(browseChannelProductionsViewModel.sortedChannelList(tilePane, choiceString));
    }

    /**
     * Switch channels.
     */
    public void switchChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    /**
     * Btn front page.
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    public void btnFrontPage(MouseEvent mouseEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    /**
     * Btn search.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void btnSearch(ActionEvent actionEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    /**
     * Btn channels.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void btnChannels(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    /**
     * Btn productions.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void btnProductions(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }
}


