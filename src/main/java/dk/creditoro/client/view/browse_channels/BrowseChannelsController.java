package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.view.IViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
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
    private ObservableList<Node> channelList;
    private Map<String, ImageView> cachedImages;
    private ViewModelFactory viewModelFactory;

    @FXML
    private ScrollPane channelPane;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField channelSearch;

    @FXML
    private HBox alphabet;

    @FXML
    ImageView imageView;


    /**
     * Lbl start menu pressed.
     *
     * @param mouseEvent the mouse event
     */


    ObservableList<String> sortingList = FXCollections.observableArrayList("A-Å", "Å-A");

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
     * Btn account.
     */
    public void btnProductions() {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    /**
     * Switch view.
     *
     * @param viewToOpen the view to open
     */
    public void switchView(String viewToOpen) {
        LOGGER.info(viewToOpen);
        viewModelFactory.getBrowseChannelProductionsViewModel().setChannelName(viewToOpen);
        viewHandler.openView(Views.BROWSE_CHANNEL_PRODUCTIONS);
    }


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        browseChannelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        this.viewHandler = viewHandler;
        this.cachedImages = new HashMap<>();
        this.viewModelFactory = viewModelFactory;

        choiceBox.setValue("A-Å");
        choiceBox.setItems(sortingList);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> sorted(t1.intValue()));

        //Add listener to channelSearch text area
        channelSearch.textProperty().bindBidirectional(browseChannelsViewModel.queryParamProperty());
        browseChannelsViewModel.listPropertyProperty().addListener((observableValue, oldValue, newValue) -> loading(newValue));
        onSearch();

    }

    private void doneLoading(TilePane tilePane) {
        Platform.runLater(() -> {
            channelPane.setContent(tilePane);
            channelList = FXCollections.observableArrayList(tilePane.getChildren());
            sorted(0);
        });

    }

    private void loading(ObservableList<Channel> channels) {
        new Thread(() -> Platform.runLater(() -> {
            ProgressIndicator pb = new ProgressIndicator();
            pb.minWidthProperty().bind(channelPane.widthProperty());
            pb.minHeightProperty().bind(channelPane.heightProperty());
            channelPane.setContent(pb);
        })).start();


        new Thread(() -> updateGrid(channels)).start();
    }

    private void updateGrid(ObservableList<Channel> channels) {
        LOGGER.info("Update grid called. BrowseChannels");
        //Remove all children from Grid
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(15, 15, 15, 15));
        tilePane.setHgap(15);
        tilePane.setVgap(15);
        tilePane.prefWidthProperty().bind(channelPane.widthProperty());

        for (Channel channel : channels) {
            imageView = cachedImages.get(channel.getIdentifier());
            if (imageView == null) {
                try {
                    imageView = new ImageView(channel.getIconUrl());
                } catch (IllegalArgumentException e) {
                    LOGGER.info(e.getMessage());
                }
                if (imageView != null) {
                    imageView.setPickOnBounds(true);
                    imageView.setId(channel.getIdentifier());
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(80);
                    imageView.setFitHeight(80);
                    imageView.setSmooth(true);

                    //Set Actions
                    imageView.setOnMouseClicked(mouseEvent -> {
                        switchView(channel.getName());
                        LOGGER.info(channel.getName());
                    });
                }
            } else {
                var message = String.format("Image %s loaded from cache.", channel.getName());
                LOGGER.info(message);
            }
            cachedImages.put(channel.getIdentifier(), imageView);
            ImageView finalImageView = imageView;
            Platform.runLater (() -> tilePane.getChildren().addAll(finalImageView));
        }
        doneLoading(tilePane);
    }


    /**
     * On search.
     */
    public void onSearch() {
        browseChannelsViewModel.search();
    }

    public void sorted(int choice) {
        TilePane tilePane = (TilePane) channelPane.getContent();
        String choiceString;
        if (choice == 1) {
            choiceString = "Å-A";
        } else {
            choiceString = "A-Å";
        }
        tilePane.getChildren().setAll(browseChannelsViewModel.sortedChannelList(tilePane, choiceString));
    }

    @FXML
    public void sortByCharacter(ActionEvent actionEvent) {
        TilePane tilePane = (TilePane) channelPane.getContent();
        tilePane.getChildren().setAll(browseChannelsViewModel.sortedByCharacter(channelList, actionEvent, alphabet));
    }

    @FXML
    public void btnFrontPage(MouseEvent mouseEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    @FXML
    public void btnSearch(ActionEvent actionEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    @FXML
    public void btnChannels(ActionEvent actionEvent) {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }
}
