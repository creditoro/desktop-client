package dk.creditoro.client.view.browse_productions;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

class BrowseProductionsViewModelTest {

    BrowseProductionsViewModel browseProductionsViewModel;

    public BrowseProductionsViewModelTest() {
        var clientFactory = new ClientFactory();
        var modelFactory = new ModelFactory(clientFactory);
        var viewModelFactory = new ViewModelFactory(modelFactory);
        browseProductionsViewModel = viewModelFactory.getBrowseProductionsViewModel();
    }

    @Test
    void queryParamProperty() {
        Assertions.assertNull(browseProductionsViewModel.queryParamProperty().getValue());
        browseProductionsViewModel.queryParamProperty().setValue("dr");
        Assertions.assertNotNull(browseProductionsViewModel.queryParamProperty().getValue());
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

        browseProductionsViewModel.queryParamProperty().setValue(null);
        browseProductionsViewModel.search();
        var productions = browseProductionsViewModel.listPropertyProperty().getSize();
        Assertions.assertNotNull(browseProductionsViewModel.listPropertyProperty());

        browseProductionsViewModel.queryParamProperty().setValue("dr");
        browseProductionsViewModel.search();
        Assertions.assertNotEquals(productions, browseProductionsViewModel.listPropertyProperty().getSize());
    }


    @Test
    void sortedList() {
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
        TilePane tilePane = new TilePane();
        Node node1 = new ImageView();
        node1.setId("40b656a3-15fa-402b-9444-c7f3672a16a3"); // tv-avisen
        Node node2 = new ImageView();
        node2.setId("b729f259-c6a7-4ab1-a040-c894c53ba4e5"); // hammerslag
        Node node3 = new ImageView();
        node3.setId("de52bcb8-d2f3-421c-b8bb-47e00d52ee9e"); // hercule
        tilePane.getChildren().addAll(node1, node3, node2);
        browseProductionsViewModel.queryParamProperty().setValue("hercule");
        browseProductionsViewModel.search();
        ;
        Assertions.assertNotEquals(tilePane.getChildren(), browseProductionsViewModel.sortedList(tilePane));
        Assertions.assertEquals("Hercule Poirot", browseProductionsViewModel.productionTitle(node3));
    }

    @Test
    void sortedByCharacter() {
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
            e.printStackTrace();
        }
        HBox alphabet = new HBox();
        Button btnT = new Button("T");
        Button btnH = new Button("H");
        Button btnC = new Button("C");
        alphabet.getChildren().addAll(btnT, btnH, btnC);

        TilePane tilePane = new TilePane();
        Node node1 = new ImageView();
        node1.setId("40b656a3-15fa-402b-9444-c7f3672a16a3"); // tv-avisen
        Node node2 = new ImageView();
        node2.setId("b729f259-c6a7-4ab1-a040-c894c53ba4e5"); // hammerslag
        Node node3 = new ImageView();
        node3.setId("de52bcb8-d2f3-421c-b8bb-47e00d52ee9e"); // hercule
        tilePane.getChildren().addAll(node1, node3, node2);

        browseProductionsViewModel.queryParamProperty().setValue("hercule");
        browseProductionsViewModel.search();
        ;

        ActionEvent ae = new ActionEvent(btnH, ActionEvent.NULL_SOURCE_TARGET);
        Assertions.assertNotEquals(tilePane.getChildren(), browseProductionsViewModel.sortedByCharacter(tilePane.getChildren(), ae, alphabet));
    }

}