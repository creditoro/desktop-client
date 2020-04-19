package dk.creditoro.client.view.browse_channels;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BrowseChannelsController implements IViewController {
    private BrowseChannelsViewModel browseChannelsViewModel;
    private ViewHandler viewHandler;

    @FXML
    private TextField channelSearch;

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
