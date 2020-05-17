package dk.creditoro.client.view.shared_viewmodel_func;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

// These are for setting up javaFX enviroment
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import javafx.embed.swing.JFXPanel;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

// Test environment is discriped here
// https://stackoverflow.com/questions/11273773/javafx-2-1-toolkit-not-initialized

/**
* FindCharacterTest
*/
public class FindCharacterTest {
	FindCharacter findCharacter;
	
	public FindCharacterTest(){
		findCharacter = new FindCharacter();
	}

	@Test
	void getCharacter(){
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JFXPanel(); // initializes JavaFX environment
				latch.countDown();
			}
		});
		try{
		latch.await();
		} catch(InterruptedException e){

		}
		HBox alphabet = new HBox();
		Button btnA = new Button("A");
		Button btnB = new Button("B");
		Button btnC = new Button("C");
		alphabet.getChildren().addAll(btnA, btnB, btnC);

		ActionEvent ae = new ActionEvent(btnB, ActionEvent.NULL_SOURCE_TARGET);
		assertEquals('B', findCharacter.getCharacter(ae, alphabet, 'A'),
				"CurrentChar is A, and the actionEvent is the B button");
		assertEquals(0, findCharacter.getCharacter(ae, alphabet, 'B'),
				"What happend to the 0 char return?");

	}

}
