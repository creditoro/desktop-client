package dk.creditoro.client.view.browse_productions;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.view.IViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.logging.Logger;

/**
 * The type Browse productions controller.
 */
public class BrowseProductionsController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private BrowseProductionsViewModel browseProductionsViewModel;
    private ViewHandler viewHandler;
    private ObservableList<Node> productionsList;

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
     * Handle search bar.
     */
    public void handleSearchBar() {
        LOGGER.info("Search button pressed");
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

    /**
     * Switch view.
     *
     * @param viewToOpen the view to open
     */
    public void switchView(String viewToOpen) {
        LOGGER.info(viewToOpen);
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        browseProductionsViewModel = viewModelFactory.getBrowseProductionsViewModel();
        this.viewHandler = viewHandler;

        //Add listener to productionSearch text area
        productionSearch.textProperty().bindBidirectional(browseProductionsViewModel.queryParamProperty());
        browseProductionsViewModel.listPropertyProperty().addListener((observableValue, oldValue, newValue) -> updateList(newValue));
        onSearch();

        // set user email
        btnAccount.setText("user.getEmail()");
    }

    /**
     * Updates list of productions
     *
     * @param productions
     */
    private void updateList(ObservableList<Production> productions) {
        LOGGER.info("Update grid called.");

        // Create TilePane for productions
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(15, 0, 0, 0));
        tilePane.prefWidthProperty().bind(productionPane.widthProperty());

        // Create VBox for each production and add title and description
        for (Production production : productions) {
            VBox vBox = createVBox(tilePane, production);
            Label title = getTitle(production);
            Text description = getDescription(production);

            // Make the VBox clickable, so it refers to given production page
            setOnMouseClicked(production, vBox);

            vBox.getChildren().addAll(title, description);
            TilePane.setMargin(vBox, new Insets(0, 0, 15, 0));
            tilePane.getChildren().add(vBox);
        }
        productionPane.setContent(tilePane);
        productionsList = FXCollections.observableArrayList(tilePane.getChildren());
    }

    /**
     * Get title from production
     *
     * @param production
     * @return
     */
    private Label getTitle(Production production) {
        Label title = new Label(production.getTitle());
        title.setFont(new Font(30));
        return title;
    }

    /**
     * Get description from production
     *
     * @param production
     * @return
     */
    private Text getDescription(Production production) {
        var numberOfCharacters = 300;
        var desc = production.getDescription().substring(0, Math.min(numberOfCharacters, production.getDescription().length()));

        Text description = new Text();
        if (production.getDescription().length() > numberOfCharacters) {
            description.setText(desc + "...");
        } else {
            description.setText(desc);
        }
        description.setFont(new Font(14));
        description.setWrappingWidth(productionPane.getWidth() - 50);
        return description;
    }

    /**
     * Set logic for when production vBox is clicked
     *
     * @param production
     * @param vBox
     */
    private void setOnMouseClicked(Production production, VBox vBox) {
        vBox.setOnMouseClicked(mouseEvent -> {
            var box = (VBox) mouseEvent.getSource();
            switchView(box.getId());
            LOGGER.info(production.getTitle());
        });
    }

    /**
     * Create VBox for productions
     *
     * @param tilePane
     * @param production
     * @return
     */
    private VBox createVBox(TilePane tilePane, Production production) {
        VBox vBox = new VBox();
        vBox.prefWidthProperty().bind(tilePane.widthProperty());
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-background-color: #EEEEEE;");
        vBox.setId(production.getIdentifier());
        return vBox;
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
        tilePane.getChildren().setAll(browseProductionsViewModel.sortedByCharacter(productionsList, actionEvent, alphabet));
    }
}
