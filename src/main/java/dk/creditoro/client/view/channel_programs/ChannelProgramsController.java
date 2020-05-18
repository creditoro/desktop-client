package dk.creditoro.client.view.channel_programs;


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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.logging.Logger;


public class ChannelProgramsController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ChannelProgramsViewModel channelProgramsViewModel;
    private ViewHandler viewHandler;
    private ObservableList<Node> productionsList;


    @FXML
    private ScrollPane productionPane;

    @FXML
    private TextField productionSearch;

    @FXML
    private ImageView imgIcon;

    @FXML
    private HBox alphabet;

    @FXML
    ComboBox<String> cbCategory;

    @FXML
    ComboBox<String> cbSort;

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
     * Switch view.
     *
     * @param viewToOpen the view to open
     */
    public void switchView(String viewToOpen) {
        LOGGER.info(viewToOpen);
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        channelProgramsViewModel = viewModelFactory.getChannelProgramsViewModel();
        this.viewHandler = viewHandler;

        //Add listener to channelSearch text area
        productionSearch.textProperty().bindBidirectional(channelProgramsViewModel.queryParamProperty());
        channelProgramsViewModel.listPropertyProperty().addListener((observableValue, oldValue, newValue) -> updateGrid(newValue));
        onSearch();

        // Add ComboBox Category and Sort.
        cbCategory.setItems(channelProgramsViewModel.listCategory());
        cbSort.setItems(channelProgramsViewModel.listSort());

        btnAccount.setText("user.getEmail()");
    }


    private void updateGrid(ObservableList<Production> productions) {
        LOGGER.info("Update grid called.");
        TilePane tp = new TilePane();
        tp.prefWidthProperty().bind(productionPane.widthProperty());


        for (Production production : productions) {
            if (!production.getChannel().getName().equals("DR1")) {
                continue;
            }
            Text description = getDescription(production);
            TitledPane tps = new TitledPane(production.getTitle(), description);
            tps.setId(production.getIdentifier());
            tps.setExpanded(false);
            tps.setPadding(new Insets(15, 0, 0, 0));

            tp.getChildren().addAll(tps);

        }
        productionPane.setContent(tp);
        productionsList = FXCollections.observableArrayList(tp.getChildren());
    }

    private Text getDescription(Production production) {
        var numberOfCharacters = 1000;
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
     * On search.
     */
    public void onSearch() {
        channelProgramsViewModel.search();
    }

    @FXML
    public void sortByCharacter(ActionEvent actionEvent) {
        TilePane tp = (TilePane) productionPane.getContent();
        tp.getChildren().setAll(channelProgramsViewModel.sortedByCharacter(productionsList, actionEvent, alphabet));
    }

    public void switchChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    public void SwitchToChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }
}


