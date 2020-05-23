package dk.creditoro.client.view.browse_productions;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.view.IViewController;
import dk.creditoro.client.view.shard_controller_func.SharedControllerFunc;
import dk.creditoro.client.view.shared_viewmodel_func.SharedViewModelFunc;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
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
 * The type Browse productions controller.
 */
public class BrowseProductionsController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private BrowseProductionsViewModel browseProductionsViewModel;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory; // I don't think it should be implemented like this?
    private ObservableList<Node> productionsList;
    private Map<String, VBox> cachedProductions;
    private SharedControllerFunc sharedControllerFunc;
    private SharedViewModelFunc sharedViewModelFunc;

    @FXML
    private ScrollPane productionPane;
    @FXML
    private TextField productionSearch;
    @FXML
    private HBox alphabet;
    @FXML
    private Button btnAccount;


    /**
     * Btn new production.
     */
    public void btnNewProduction() {
        LOGGER.info("Create production button pressed");
    }

    /**
     * Btn account.
     */
    public void btnAccount() {
        LOGGER.info("Account button pressed.");
    }

    /**
     * Btn channels.
     */
    public void btnChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    /**
     * Btn productions.
     *
     * @param actionEvent the action event
     */
    public void btnProductions(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewModelFactory = viewModelFactory;
        browseProductionsViewModel = viewModelFactory.getBrowseProductionsViewModel();
        this.viewHandler = viewHandler;
        this.cachedProductions = new HashMap<>();
        sharedControllerFunc = new SharedControllerFunc();
        sharedViewModelFunc = new SharedViewModelFunc();

        //Add listener to productionSearch text area
        productionSearch.textProperty().bindBidirectional(browseProductionsViewModel.queryParamProperty());
        browseProductionsViewModel.listPropertyProperty().addListener((observableValue, oldValue, newValue) -> loading(newValue));
        onSearch();

        // set user email
        btnAccount.setText("user.getEmail()");
    }

    private void loading(ObservableList<Production> productions) {
        new Thread(() -> Platform.runLater(() -> {
            ProgressIndicator pb = new ProgressIndicator();
            pb.minWidthProperty().bind(productionPane.widthProperty());
            pb.minHeightProperty().bind(productionPane.heightProperty());
            productionPane.setContent(pb);
        })).start();


        new Thread(() -> updateList(productions)).start();
    }

    private void doneLoading(TilePane tilePane) {
        Platform.runLater(() -> {
            productionPane.setContent(tilePane);
            productionsList = FXCollections.observableArrayList(tilePane.getChildren());
        });
    }

    /**
     * Updates list of productions
     *
     * @param productions
     */
    private void updateList(ObservableList<Production> productions) {
        LOGGER.info("Update grid called. BrowseProductions");
        // Create TilePane for productions
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(15, 0, 0, 0));
        tilePane.prefWidthProperty().bind(productionPane.widthProperty());
        List<Node> children = computeChildren(productions, tilePane);
        Platform.runLater(() -> tilePane.getChildren().addAll(children));
        doneLoading(tilePane);
    }

    /**
     * Compute children list.
     *
     * @param productions the productions
     * @param tilePane    the tile pane
     * @return the list
     */
    public List<Node> computeChildren(ObservableList<Production> productions, TilePane tilePane) {
        List<Node> list = new ArrayList<>();
        // Create VBox for each production and add title and description
        for (Production production : productions) {
            sharedControllerFunc.generateChildren(tilePane, list, production, cachedProductions, productionPane, viewModelFactory, viewHandler);
        }
        return list;
    }

    /**
     * On search.
     */
    public void onSearch() {
        browseProductionsViewModel.search();
    }

    /**
     * Sort by character.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void sortByCharacter(ActionEvent actionEvent) {
        TilePane tilePane = (TilePane) productionPane.getContent();
        tilePane.getChildren().setAll(sharedViewModelFunc.sortedByCharacter(productionsList, actionEvent, alphabet, browseProductionsViewModel.listPropertyProperty()));
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
}
