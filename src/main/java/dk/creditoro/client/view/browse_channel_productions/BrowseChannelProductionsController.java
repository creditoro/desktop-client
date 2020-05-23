package dk.creditoro.client.view.browse_channel_productions;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.view.IViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
    private BrowseChannelProductionsViewModel browseChannelProductionsViewModel;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;
    private ObservableList<Node> productionsList;
    private Map<String, VBox> cachedProductions;
    private Map<String, List<Production>> cachedProductionMap;

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
     * The Sorting list.
     */
    ObservableList<String> sortingList = FXCollections.observableArrayList("A-Å", "Å-A");

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
     * Switch view.
     *
     * @param viewToOpen the view to open
     * @param channelId  the channel id
     */
    public void switchView(String viewToOpen, String channelId) {
        LOGGER.info(viewToOpen);
        this.viewModelFactory.getProductionViewModel().setId(viewToOpen);
        this.viewModelFactory.getProductionViewModel().setChannelId(channelId);
        this.viewHandler.openView(Views.PRODUCTION);
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewModelFactory = viewModelFactory;
        browseChannelProductionsViewModel = viewModelFactory.getBrowseChannelProductionsViewModel();
        this.viewHandler = viewHandler;
        this.cachedProductions = new HashMap<>();

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
            VBox vBox = cachedProductions.get(production.getIdentifier());
            if (vBox == null) {
                vBox = createVBox(tilePane, production);
                Label title = getTitle(production);
                Text description = getDescription(production);

                // Make the VBox clickable, so it refers to given production page
                setOnMouseClicked(production, vBox);

                vBox.getChildren().addAll(title, description);
                TilePane.setMargin(vBox, new Insets(0, 0, 15, 0));
                cachedProductions.put(production.getIdentifier(), vBox);
            }
            list.add(vBox);
        }
        return list;
    }

    private VBox createVBox(TilePane tilepane, Production production) {
        VBox vBox = new VBox();
        vBox.prefWidthProperty().bind(tilepane.widthProperty());
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-background-color: #EEEEEE;");
        vBox.setId(production.getIdentifier());
        return vBox;
    }

    private Label getTitle(Production production) {
        Label title = new Label(production.getTitle());
        title.setFont(new Font(30));
        return title;
    }

    private Text getDescription(Production production) {
        var maxNumberOfCharacters = 300;
        var desc = production.getDescription().substring(0, Math.min(maxNumberOfCharacters, production.getDescription().length()));

        Text description = new Text();
        if (production.getDescription().isEmpty()) {
            description.setText("Ingen programbeskrivelse at vise");
        } else if (production.getDescription().length() > maxNumberOfCharacters) {
            description.setText(desc + "...");
        } else {
            description.setText(desc);
        }

        description.setFont(new Font(14));
        description.setWrappingWidth(productionPane.getPrefWidth() - 50);
        return description;
    }

    private void setOnMouseClicked(Production production, VBox description) {
        description.setOnMouseClicked(mouseEvent -> {
            // Check which VBox was pressed
            var box = (VBox) mouseEvent.getSource();
            //Set title in productionViewModel
            viewModelFactory.getProductionViewModel().setTitle(production.getTitle());
            // set channel Neme in addCreditViewModel
            viewModelFactory.getAddCreditViewModel().setChannelName(production.getChannel().getName());
            // Set production in addCreditViewModel
            viewModelFactory.getAddCreditViewModel().setProduction(production);
            // Set boolean
            viewModelFactory.getProductionViewModel().setWhichView(true);
            // Changing view to chosen production
            switchView(box.getId(), production.getChannel().getIdentifier());
            LOGGER.info(production.getTitle());
        });
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


