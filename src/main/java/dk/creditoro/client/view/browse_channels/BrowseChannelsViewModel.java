package dk.creditoro.client.view.browse_channels;


import dk.creditoro.client.model.channel.IChannelModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The type Browse channels view model.
 */
public class BrowseChannelsViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final StringProperty queryParam = new SimpleStringProperty();
    private final IChannelModel channelModel;


    private ArrayList<String> channelList = new ArrayList();
    private ArrayList<String> searchList = new ArrayList();
    //public ObservableList<String> searchList = FXCollections.observableArrayList();

    /**
     * Instantiates a new Login view model.
     *
     * @param channelModel the channel model
     */
    public BrowseChannelsViewModel(IChannelModel channelModel) {
        this.channelModel = channelModel;
    }

    public StringProperty queryParamProperty() {
        return queryParam;
    }

    public void search() {
        //channelModel.search(queryParamProperty().get());

        if(queryParam.getValue() == null)
        {
            searchList = channelList;
            return;
        }

        //searchList = FXCollections.observableArrayList();
        searchList = new ArrayList();
        //search through channelList
        for(int i = 0; i < channelList.size(); i++)
        {
            if(channelList.get(i).toLowerCase().contains(queryParam.getValue().toLowerCase()))
            {
                //Add channel to searchList
                searchList.add(channelList.get(i));
            }
        }
    }

    public ArrayList<String> getList()
    {
        //Return searchList to be used when updating grid
        return searchList;
    }

    //Add channels from API to channelList
    public void getChannels()
    {
        //Get list from rest API
        channelList.add("TV2");
        channelList.add("DR1");
        channelList.add("TV2 Play");
        channelList.add("Netflix");
        channelList.add("ViaPlay");

        searchList = channelList;
    }
}
