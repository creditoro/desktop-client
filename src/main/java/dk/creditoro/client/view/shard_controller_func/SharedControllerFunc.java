package dk.creditoro.client.view.shard_controller_func;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.view.shared_viewmodel_func.SharedViewModelFunc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * The type Shared controller func.
 */
public class SharedControllerFunc {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    ObservableList<String> sortingList = FXCollections.observableArrayList("A-Å", "Å-A");


    /**
     * Generate children.
     *
     * @param tilePane          the tile pane
     * @param list              the list
     * @param production        the production
     * @param cachedProductions the cached productions
     * @param productionPane    the production pane
     * @param viewModelFactory  the view model factory
     * @param viewHandler       the view handler
     */
    public void generateChildren(TilePane tilePane, List<Node> list, Production production, Map<String, VBox> cachedProductions, ScrollPane productionPane, ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        VBox vBox = cachedProductions.get(production.getIdentifier());
        if (vBox == null) {
            vBox = createVBox(tilePane, production);
            Label title = getTitle(production);
            Text description = getDescription(production, productionPane);

            // Make the VBox clickable, so it refers to given production page
            setOnMouseClicked(production, vBox, viewModelFactory, viewHandler, cachedProductions);

            vBox.getChildren().addAll(title, description);
            TilePane.setMargin(vBox, new Insets(0, 0, 15, 0));
            cachedProductions.put(production.getIdentifier(), vBox);
        }
        list.add(vBox);
    }


    /**
     * Create VBox for productions
     *
     * @param tilePane   the tile pane
     * @param production the production
     * @return v box
     */
    public VBox createVBox(TilePane tilePane, Production production) {
        VBox vBox = new VBox();
        vBox.prefWidthProperty().bind(tilePane.widthProperty());
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-background-color: #EEEEEE;");
        vBox.setId(production.getIdentifier());
        return vBox;
    }

    /**
     * Get title from production
     *
     * @param production the production
     * @return title title
     */
    public Label getTitle(Production production) {
        Label title = new Label(production.getTitle());
        title.setFont(new Font(30));
        return title;
    }

    /**
     * Get description from production
     *
     * @param production     the production
     * @param productionPane the production pane
     * @return description description
     */
    public Text getDescription(Production production, ScrollPane productionPane) {
        var numberOfCharacters = 300;
        var desc = production.getDescription().substring(0, Math.min(numberOfCharacters, production.getDescription().length()));

        Text description = new Text();
        if (production.getDescription().isEmpty()) {
            description.setText("Ingen programbeskrivelse at vise");
        } else if (production.getDescription().length() > numberOfCharacters) {
            description.setText(desc + "...");
        } else {
            description.setText(desc);
        }
        description.setFont(new Font(14));
        description.setWrappingWidth(productionPane.getPrefWidth() - 50);
        return description;
    }

    private void setOnMouseClicked(Production production, VBox vBox, ViewModelFactory viewModelFactory, ViewHandler viewHandler, Map<String, VBox> cachedProductions) {
        vBox.setOnMouseClicked(mouseEvent -> {
            // Check which VBox was pressed
            var box = (VBox) mouseEvent.getSource();
            //Set title in productionViewModel
            viewModelFactory.getProductionViewModel().setTitle(production.getTitle());
            // set id of production
            viewModelFactory.getProductionViewModel().setId(production.getIdentifier());
            // get credits
            viewModelFactory.getProductionViewModel().getCredits();
            // set channel Name in addCreditViewModel
            viewModelFactory.getAddCreditViewModel().setChannelName(production.getChannel().getName());
            // Set production in addCreditViewModel
            viewModelFactory.getAddCreditViewModel().setProduction(production);
            // Set boolean to check which view to return to
            viewModelFactory.getProductionViewModel().setWhichView(cachedProductions.size() <= 1000);
            // Changing view to chosen production
            switchView(box.getId(), production.getChannel().getIdentifier(), viewModelFactory, viewHandler);
            LOGGER.info(production.getTitle());
        });
    }

    /**
     * Switch view.
     *
     * @param viewToOpen       the view to open
     * @param channelId        the channel id
     * @param viewModelFactory the view model factory
     * @param viewHandler      the view handler
     */
    public void switchView(String viewToOpen, String channelId, ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        LOGGER.info(viewToOpen);
        viewModelFactory.getProductionViewModel().setId(viewToOpen);
        viewModelFactory.getProductionViewModel().setChannelId(channelId);
        viewHandler.openView(Views.PRODUCTION);
    }


    /**
     * Sort from A-Å and reverse
     *
     * @param choice                the choice int
     * @param productionPane        the production pane
     * @param sharedViewModelFunc   the shared view model
     */
    public void sorted(int choice, ScrollPane productionPane, SharedViewModelFunc sharedViewModelFunc) {
        TilePane tilePane = (TilePane) productionPane.getContent();
        String choiceString;
        if (choice == 1) {
            choiceString = "Å-A";
        } else {
            choiceString = "A-Å";
        }
        tilePane.getChildren().setAll(sharedViewModelFunc.sortedProductionList(tilePane, choiceString));
    }


    /**
     * Choice Box to apply sort from A-Å
     *
     * @param choiceBox             the choice box
     * @param productionPane        the production pane
     * @param sharedViewModelFunc   the shared view model
     */
    public void createChoiceBox(ChoiceBox<String> choiceBox, ScrollPane productionPane, SharedViewModelFunc sharedViewModelFunc){
        choiceBox.setValue("A-Å");
        choiceBox.setItems(sortingList);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> sorted(t1.intValue(), productionPane, sharedViewModelFunc));
    }
}
