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
     * Btn new channel.
     */
    public void btnNewChannel() {
        LOGGER.info("Open popup box");
    }

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

    /**
     * Btn channels.
     */
    public void btnChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
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

        //Add listener to channelSearch text area
        productionSearch.textProperty().bindBidirectional(browseProductionsViewModel.queryParamProperty());
        browseProductionsViewModel.listPropertyProperty().addListener((observableValue, oldValue, newValue) -> updateGrid(newValue));
        onSearch();

        // set user email
        btnAccount.setText("user.getEmail()");
    }

    private void updateGrid(ObservableList<Production> productions) {
        LOGGER.info("Update grid called.");

        //Remove all children from Grid
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(15, 0, 0, 0));
        tilePane.prefWidthProperty().bind(productionPane.widthProperty());

        for (Production production : productions) {
            VBox vBox = new VBox();
            vBox.prefWidthProperty().bind(tilePane.widthProperty());
            vBox.setPadding(new Insets(15, 15, 15, 15));
            vBox.setStyle("-fx-background-color: #EEEEEE;");
            vBox.setId(production.getIdentifier());

            Label title = new Label(production.getTitle());
            title.setFont(new Font(30));

            Label description = new Label("Ipsa et quod in voluptatum dolorem. Numquam occaecati distinctio " +
                    "praesentium nam nihil ullam. Quas veniam voluptatem qui repellendus numquam ut tenetur et. Ut est " +
                    "voluptatibus ipsum beatae facere cumque molestiae. A aliquid minus veritatis eos omnis dolore unde. " +
                    "Voluptatum reprehenderit voluptatem aut culpa ad autem.");

            description.setFont(new Font(14));
            description.setPadding(new Insets(0, 0, 10, 0));
            description.setWrapText(true);

            vBox.setOnMouseClicked(mouseEvent -> {
                var box = (VBox) mouseEvent.getSource();
                switchView(box.getId());
                LOGGER.info(production.getTitle());
            });

            vBox.getChildren().addAll(title, description);
            tilePane.getChildren().addAll(vBox);
        }
        productionPane.setContent(tilePane);
        productionsList = FXCollections.observableArrayList(tilePane.getChildren());
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
