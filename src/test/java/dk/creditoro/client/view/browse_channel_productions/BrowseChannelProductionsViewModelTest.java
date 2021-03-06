package dk.creditoro.client.view.browse_channel_productions;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.crud.Channel;
import dk.creditoro.client.view.browse_channels.BrowseChannelsViewModel;
import dk.creditoro.client.view.browse_productions.BrowseProductionsViewModel;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class BrowseChannelProductionsViewModelTest {

    BrowseChannelProductionsViewModel browseChannelProductionsViewModel;
    BrowseChannelsViewModel browseChannelsViewModel;

    public BrowseChannelProductionsViewModelTest() {
        var clientFactory = new ClientFactory();
        var modelFactory = new ModelFactory(clientFactory);
        var viewModelFactory = new ViewModelFactory(modelFactory);
        browseChannelProductionsViewModel = viewModelFactory.getBrowseChannelProductionsViewModel();
        browseChannelsViewModel = viewModelFactory.getBrowseChannelsViewModel();

    }

    @Test
    void queryParamProperty() {
        Assertions.assertNull(browseChannelProductionsViewModel.queryParamProperty().getValue());
        browseChannelProductionsViewModel.queryParamProperty().setValue("dr");
        Assertions.assertNotNull(browseChannelProductionsViewModel.queryParamProperty().getValue());
    }

    @Test
    void search() {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JFXPanel(); // initializes JavaFX environment
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
        }

        browseChannelProductionsViewModel.queryParamProperty().setValue(null);
        browseChannelProductionsViewModel.search();
        var productions = browseChannelProductionsViewModel.listPropertyProperty().getSize();

        Assertions.assertNotNull(browseChannelProductionsViewModel.listPropertyProperty());


        browseChannelProductionsViewModel.queryParamProperty().setValue("dr");
        browseChannelProductionsViewModel.search();
        Assertions.assertNotEquals(productions, browseChannelProductionsViewModel.listPropertyProperty().getSize());
    }

    @Test
    void qSearch() {
        browseChannelsViewModel.search();
        browseChannelProductionsViewModel.createProductionMap();
        Channel channel = browseChannelsViewModel.listPropertyProperty().get(0);
        browseChannelProductionsViewModel.setChannelName(channel.getName());
        Assertions.assertNotNull(browseChannelProductionsViewModel.qSearch());
        char firstLetter = browseChannelProductionsViewModel.listPropertyProperty().get(0).getTitle().charAt(0);
        browseChannelProductionsViewModel.queryParamProperty().setValue(firstLetter+"");
        Assertions.assertNotNull(browseChannelProductionsViewModel.qSearch());
    }

    @Test
    void channelName() {
        browseChannelProductionsViewModel.setChannelName("test");
        Assertions.assertEquals("test",browseChannelProductionsViewModel.getChannelName().getValue());
    }
}