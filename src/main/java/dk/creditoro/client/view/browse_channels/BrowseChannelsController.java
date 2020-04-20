package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

public class BrowseChannelsController implements IViewController {
    private BrowseChannelsViewModel browseChannelsViewModel;
    private ViewHandler viewHandler;

    @FXML
    private ImageView imgAddChannel;

    @FXML
    private GridPane channelGrid;


    @FXML
    private TextField channelSearch;

    public void BtnNewChannel(MouseEvent mouseEvent) {
        //Popup box
    }

    public void handleSearchBar(ActionEvent actionEvent) {
        //GetChannels (searchBar.getText());
    }

    public void btnAccount(ActionEvent actionEvent) {

    }

    public void switchView(String viewToOpen)
    {
        System.out.println(viewToOpen);
    }

    public void updateGrid()
    {
        //Remove all children from Grid
        channelGrid.getChildren().clear();

        //Get list
        ArrayList<String> temp = browseChannelsViewModel.getList();

        int h = channelGrid.getColumnCount();
        int v = channelGrid.getRowCount();
        int count = 0;

        for(int i = 0; i < v; i++)
        {
            for(int j = 0; j < h; j++)
            {
                if(count >= temp.size())
                {
                    return;
                }
                //Set logo
                File file = new File("/Users/jakobrasmussen/IdeaProjects/desktop-client/src/main/resources/dk/creditoro/client/res/TV_2_logo.png");
                Image img = new Image(file.toURI().toString());
                ImageView image = new ImageView(img);

                //Set scale
                image.setScaleX(0.2);
                image.setScaleY(0.2);

                //Set id
                image.setId(temp.get(count));
                image.setPickOnBounds(true);

                //Set Actions
                image.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ImageView img = (ImageView) mouseEvent.getSource();

                        switchView(img.getId());
                    }
                });

                channelGrid.add(image, j, i);
                count++;
            }
        }
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        browseChannelsViewModel = viewModelFactory.getBrowseChannelsViewModel();
        this.viewHandler = viewHandler;

        //Add listener to channelSearch text area
        channelSearch.textProperty().bindBidirectional(browseChannelsViewModel.queryParamProperty());
        browseChannelsViewModel.queryParamProperty().addListener((observableValue, oldValue, newValue) -> OnSearch());
        browseChannelsViewModel.queryParamProperty().addListener((observableValue, oldValue, newValue) -> updateGrid());

        //Add listener to list
        //browseChannelsViewModel.getList().addListener((ListChangeListener<? super String>) observable -> updateGrid());
        //channelGrid.textProperty().bindBidirectional(browseChannelsViewModel.queryParamProperty());

        //Get All Channels
        browseChannelsViewModel.getChannels();
        updateGrid();
    }

    public void OnSearch() {
        browseChannelsViewModel.search();
    }
}
