package dk.creditoro.client.view.browse_productions;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.crud.Production;
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
import java.util.*;
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
        browseProductionsViewModel.queryParamProperty().setValue("ø");
        browseProductionsViewModel.search();
        var result = browseProductionsViewModel.listPropertyProperty();
        for (int i = 0; i < result.getSize(); i++){
            Node node = new ImageView();
            Production production = result.get(i);
            node.setId(production.getIdentifier());
            tilePane.getChildren().add(node);
        }
        Assertions.assertNotEquals(tilePane.getChildren(), browseProductionsViewModel.sortedList(tilePane));
        var title1 = browseProductionsViewModel.productionTitle(tilePane.getChildren().get(0));;
        var title2 = browseProductionsViewModel.productionTitle(tilePane.getChildren().get(1));
        Assertions.assertNotEquals(title1, title2);
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
        Button btnA = new Button("A");
        Button btnB = new Button("B");
        Button btnC = new Button("C");
        alphabet.getChildren().addAll(btnA, btnB, btnC);

        TilePane tilePane = new TilePane();

        browseProductionsViewModel.queryParamProperty().setValue("ø");
        browseProductionsViewModel.search();
        ArrayList<Integer> random = new ArrayList<>();
        var result = browseProductionsViewModel.listPropertyProperty();
        for (int i = 0; i < result.getSize(); i++){
            random.add(i);
        }
        Collections.shuffle(random);

        for (int i = 0; i < result.getSize(); i++){
            Node node = new ImageView();
            Production production = result.get(random.get(i));
            node.setId(production.getIdentifier());
            tilePane.getChildren().add(node);
        }
        ActionEvent ae = new ActionEvent(btnA, ActionEvent.NULL_SOURCE_TARGET);
        var sortResult = browseProductionsViewModel.sortedByCharacter(tilePane.getChildren(), ae, alphabet).get(0);
        Assertions.assertNotEquals(tilePane.getChildren().get(0), sortResult);
    }

}
