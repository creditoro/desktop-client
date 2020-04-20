package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.view.IViewController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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

    private Map<String, ImageView> cachedImages;


    @FXML
    private GridPane channelGrid;


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
        channelGrid.getChildren().clear();

        int maxColumns = channelGrid.getColumnCount();
        int maxRows = channelGrid.getRowCount();
        int column = 0;
        int row = 0;
        int count = 0;
        int availableSlots = maxColumns * maxRows;
        for (Channel channel : channels) {
            if (count >= availableSlots) {
                return;
            }
            var image = cachedImages.get(channel.getIdentifier());
            if (image == null) {
                image = new ImageView(channel.getIconUrl());
                image.setPickOnBounds(true);
                image.setId(channel.getIdentifier());
                image.setPreserveRatio(true);
                image.setFitWidth(80);
                image.setSmooth(true);
                //Set Actions
                image.setOnMouseClicked(mouseEvent -> {
                    var img = (ImageView) mouseEvent.getSource();
                    switchView(img.getId());
                });
            }
            cachedImages.put(channel.getIdentifier(), image);

            channelGrid.add(image, column, row);
            column = (column + 1) % maxColumns;
            if (column == 0) {
                row = (row + 1) % maxRows;
            }
            count++;
        }
    }


    /**
     * On search.
     */
    public void onSearch() {
        browseChannelsViewModel.search();
    }
}
