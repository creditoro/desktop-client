package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class BrowseChannelsController implements IViewController {
    private BrowseChannelsViewModel browseChannelsViewModel;
    private ViewHandler viewHandler;

    @FXML
    private TextField channelSearch;
    public ImageView imgAddChannel;
    public GridPane channelGrid;
    public TextField searchBar;

    public void BtnNewChannel(MouseEvent mouseEvent)
    {
        //Popup box
    }

    public void handleSearchBar(ActionEvent actionEvent)
    {
        //GetChannels (searchBar.getText());
    }

    public void btnAccount(ActionEvent actionEvent)
    {

    }

    public void switchView(ActionEvent actionEvent)
    {

    }

    public void BtnSearch(ActionEvent actionEvent) {
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        browseChannelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        this.viewHandler = viewHandler;
        // browseChannelsViewModel.get()
        channelSearch.textProperty().bindBidirectional(browseChannelsViewModel.queryParamProperty());
    }

    public void OnSearch() {
        browseChannelsViewModel.search();
    }
}
