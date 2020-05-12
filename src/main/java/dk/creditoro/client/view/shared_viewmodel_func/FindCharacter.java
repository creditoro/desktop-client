package dk.creditoro.client.view.shared_viewmodel_func;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * The type Find character.
 */
public class FindCharacter {

    /**
     * Gets character.
     *
     * @param actionEvent the action event
     * @param alphabet    the alphabet
     * @return the character
     */
    public char getCharacter(ActionEvent actionEvent, HBox alphabet, char currentCharacter) {
        char character = 0;
        var i = 65;
        for (Node node : alphabet.getChildren()) {
            if (node == actionEvent.getSource()) {
                character = (char) i;
                Button button = (Button) actionEvent.getSource();
                var styleClass = button.getStyleClass();
                if (currentCharacter == character) {
                    styleClass.remove("bold");
                    currentCharacter = 0;
                    return currentCharacter;
                } else {
                    currentCharacter = character;
                    styleClass.add("bold");
                }
            } else {
                Button button = (Button) node;
                var styleClass = button.getStyleClass();
                styleClass.remove("bold");
            }
            i++;
        }
        return character;
    }
}
