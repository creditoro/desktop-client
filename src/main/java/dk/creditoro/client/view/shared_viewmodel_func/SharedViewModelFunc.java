package dk.creditoro.client.view.shared_viewmodel_func;

import dk.creditoro.client.model.crud.Production;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

import java.util.Comparator;


/**
 * The type Find character.
 */
public class SharedViewModelFunc {

    private char currentCharacter;
    private final ObservableList<Production> productionsList = FXCollections.observableArrayList();
    private ListProperty<Production> productions = new SimpleListProperty<>(productionsList);

    /**
     * Production title string.
     *
     * @param node the node
     * @return the string
     */


    public String productionTitle(Node node) {
        var identifier = node.getId();
        for (int i = 0; i < productions.getSize(); i++) {
            Production production = productions.get(i);
            if (production.getIdentifier().equals(identifier)) {
                return production.getTitle();
            }
        }
        return "";
    }

    /**
     * Sorted by character observable list.
     *
     * @param list         the list
     * @param actionEvent  the action event
     * @param alphabet     the alphabet
     * @param listProperty the list property
     * @return the observable list
     */
    public ObservableList<Node> sortedByCharacter(ObservableList<Node> list, ActionEvent actionEvent, HBox alphabet, ListProperty<Production> listProperty) {
        SharedViewModelFunc sharedViewModelFunc = new SharedViewModelFunc();
        ObservableList<Node> observableList = FXCollections.observableArrayList(list);
        char character = sharedViewModelFunc.getCharacter(actionEvent, alphabet, currentCharacter);
        currentCharacter = character;
        setListProperty(listProperty);


        if (character != 0) {

            observableList.removeIf(node -> !productionTitle(node).toUpperCase().startsWith(String.valueOf(character)));
        } else {
            return observableList;
        }
        return observableList;
    }

    /**
     * Gets character.
     *
     * @param actionEvent      the action event
     * @param alphabet         the alphabet
     * @param currentCharacter the current character
     * @return the character
     */
    public char getCharacter(ActionEvent actionEvent, HBox alphabet, char currentCharacter) {
        // This loops through every node in alphabet and removes the bold styleClass
        alphabet.getChildren().forEach(node -> ((Button) node).getStyleClass().remove("bold"));

        // This goes through every node until it finds the one that equals
        // to actionEvent,
        // Stream and filter + Findfirst is easy, so then it finds the first
        // node that matches is stops.
        var btnOptional = alphabet.getChildren().stream().filter(node ->
                node == actionEvent.getSource()).findFirst();
        // if btnOptional is empty we should just return
        if (btnOptional.isEmpty()) {
            return 0;
        }

        var btn = ((Button) btnOptional.get());

        // Here we check if the bold should be toggled
        if (btn.getText().charAt(0) == currentCharacter) {
            btn.getStyleClass().remove("bold");
            return 0;
        } else {
            btn.getStyleClass().add("bold");
            // this return the character it found.
            return btn.getText().charAt(0);
        }
    }


    public void setListProperty(ListProperty<Production> listProperty) {
        productions = listProperty;
    }

    /**
     * Sorted from A-Å and reverse observable list
     *
     * @param tilePane      the tile pane
     * @param sortingMethod the sorting method
     * @return
     */
    public ObservableList<Node> sortedProductionList(TilePane tilePane, String sortingMethod) {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(tilePane.getChildren());
        Comparator<Node> comparator = Comparator.comparing(this::productionTitle);
        if (sortingMethod.equals("Å-A")) {

            workingCollection.sort(comparator.reversed());
            return workingCollection;
        }
        workingCollection.sort(comparator);
        return workingCollection;
    }
}

