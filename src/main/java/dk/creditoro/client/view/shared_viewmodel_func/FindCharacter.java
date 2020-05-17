package dk.creditoro.client.view.shared_viewmodel_func;

import javafx.event.ActionEvent;
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
		// This loops through every node in alphabet and removes the bold styleClass
		alphabet.getChildren().forEach(node -> ((Button)node).getStyleClass().remove("bold"));
		
		// This goes through every node until it finds the one that quals
		// to actionEvent, 
		// Stream and filter + Findfirst is lasy, so then it finds the first
		// node that matchtes is stops. 
		var btnOptional = alphabet.getChildren().stream().filter(node -> 
				node == actionEvent.getSource()).findFirst();
		if (btnOptional.isPresent()){
			var btn = ((Button)btnOptional.get());

			// Here we check if the bold should be toggled
			if( btn.getText().charAt(0) == currentCharacter ){
				btn.getStyleClass().remove("bold");
				return 0;
			} else {
				btn.getStyleClass().add("bold");
				return btn.getText().charAt(0);
			}
		} else { return 0; }

		// this return the character it found.
    }
}
