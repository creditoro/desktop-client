package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.view.IViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The type Browse channels controller.
 */
public class BrowseChannelsController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private BrowseChannelsViewModel browseChannelsViewModel;
    private ViewHandler viewHandler;
    private ObservableList<Channel> channels;

    private Map<String, ImageView> cachedImages;


    @FXML
    private ScrollPane channelPane;


    @FXML
    private TextField channelSearch;

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
        browseChannelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        this.viewHandler = viewHandler;
        this.cachedImages = new HashMap<>();

        //Add listener to channelSearch text area
        channelSearch.textProperty().bindBidirectional(browseChannelsViewModel.queryParamProperty());
        browseChannelsViewModel.listPropertyProperty().addListener((observableValue, oldValue, newValue) -> updateGrid(newValue));
        onSearch();
    }


    private void updateGrid(ObservableList<Channel> channels) {
        LOGGER.info("Update grid called.");

        //Remove all children from Grid
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(15, 15, 15, 15));
        tilePane.setHgap(15);
        tilePane.prefWidthProperty().bind(channelPane.widthProperty());

        for (Channel channel : channels) {
            var image = cachedImages.get(channel.getIdentifier());
            if (image == null) {
                try {
                    image = new ImageView(channel.getIconUrl());
                } catch (IllegalArgumentException e) {
                    LOGGER.info(e.getMessage());
                    continue;
                }
                image.setPickOnBounds(true);
                image.setId(channel.getIdentifier());
                image.setPreserveRatio(true);
                image.setFitWidth(80);
                image.setFitHeight(80);
                image.setSmooth(true);
                //Set Actions
                image.setOnMouseClicked(mouseEvent -> {
                    var img = (ImageView) mouseEvent.getSource();
                    switchView(img.getId());
                    LOGGER.info(channel.getName());
                });
            } else {
                var message = String.format("Image %s loaded from cache.", channel.getName());
                LOGGER.info(message);
            }
            cachedImages.put(channel.getIdentifier(), image);
            tilePane.getChildren().addAll(image);
        }
        this.channels = channels;
        channelPane.setContent(tilePane);
    }


    /**
     * On search.
     */
    public void onSearch() {
        browseChannelsViewModel.search();
    }

    public void sorted() {
        TilePane tilePane = (TilePane) channelPane.getContent();
        tilePane.getChildren().setAll(browseChannelsViewModel.sortedList(tilePane));
    }
}
