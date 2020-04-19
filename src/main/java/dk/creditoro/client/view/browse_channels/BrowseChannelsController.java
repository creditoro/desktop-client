package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.logging.Logger;

public class BrowseChannelsController implements IViewController {
    private BrowseChannelsViewModel browseChannelsViewModel;
    private ViewHandler viewHandler;

    @FXML
    private TextField channelSearch;
    public ImageView imgAddChannel;
    public GridPane channelGrid;

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

    public void updateGrid()
    {
        String[] temp = {"TV2", "DR1", "TV2 Zulu", "test", "test1", "test2", "test3", "fuckmitliv"};

        int h = channelGrid.getColumnCount();
        int v = channelGrid.getRowCount();
        int count = 0;

        for(int i = 0; i < v; i++)
        {
            for(int j = 0; j < h; j++)
            {
                if(count >= temp.length)
                {
                    return;
                }
                Label lbl = new Label(temp[count]);
                channelGrid.add(lbl, j, i);
                count++;
            }
        }
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        browseChannelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        this.viewHandler = viewHandler;
        // browseChannelsViewModel.get()
        channelSearch.textProperty().bindBidirectional(browseChannelsViewModel.queryParamProperty());
        //browseChannelsViewModel.queryParamProperty().addListener((observableValue, oldValue, newValue) -> updateGrid(newValue));

        //channelGrid.textProperty().bindBidirectional(browseChannelsViewModel.queryParamProperty());
        updateGrid();
    }

    public void OnSearch() {
        browseChannelsViewModel.search();
    }
}
