package dk.creditoro.client.view.production;


import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.view.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * The type Browse channels controller.
 */
public class ProductionController implements IViewController {
    private ProductionViewModel productionViewModel;
    private ViewHandler viewHandler;

    @FXML public TextField channelSearch;
    @FXML public VBox creditList;
    @FXML public ChoiceBox choiceSeason;
    @FXML public ChoiceBox choiceEpisode;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        //Update list
        updateList();
    }

    public void updateList() {
        //Get list of credits
        //****TEMP****
        String[] list = {"Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7"};
        choiceEpisode.getItems().add("All");
        choiceSeason.getItems().add("Season 1");

        //Foreach credit, create clickable node
        for (String l : list) {
            //Create Pane
            Pane pane = new Pane();
            pane.setPrefHeight(100);
            pane.setMinHeight(100);
            pane.setStyle("-fx-background-color: EEEEEE");

            //Create VBox
            VBox vbox = new VBox();
            vbox.fillWidthProperty();
            vbox.setPrefHeight(100);
            vbox.setSpacing(5);

            //Create title
            Label title = new Label(l);
            title.setFont(new Font(30));
            title.setPadding(new Insets(0, 0, 0, 5));

            //Create description
            Label description = new Label("Beskrivelse");
            description.setWrapText(true);
            description.setFont(new Font(18));
            description.prefHeight(70);
            description.prefWidth(vbox.getWidth());
            description.setMaxWidth(680);
            description.setPadding(new Insets(0, 0, 0, 5));
            description.setAlignment(Pos.TOP_LEFT);

            //Choice boxes
            choiceEpisode.getItems().add(l);

            //Add children
            vbox.getChildren().add(title);
            vbox.getChildren().add(description);

            pane.getChildren().add(vbox);
            creditList.getChildren().add(pane);
        }

        choiceSeason.getSelectionModel().selectFirst();
        choiceEpisode.getSelectionModel().selectFirst();
    }


    public void btnAccount(ActionEvent actionEvent) {
        //Code
    }

    public void btnNewCredit(MouseEvent mouseEvent) {
        //Code
    }

    public void btnChangeProduction(ActionEvent actionEvent) {
        //Code
    }
}
