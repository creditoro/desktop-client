package dk.creditoro.client.model.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
* ProductionTest
*/
public class ProductionTest {
	Production production;
	String message = "Maybe production changed?";

	public ProductionTest(){
		production = new Production("10-10-10", "Hornbœk langt ude", new User("madeUpIdentifier","phone","email","password","producer","producer", "none"),
				new Channel("madeUpIdentifier","TV2 ØST","iconUrl"));
	}

	@Test void getIdentifier(){
		assertEquals("10-10-10", production.getIdentifier(), message);
	}
	
	@Test void getTitle(){
		assertEquals("Hornbœk langt ude", production.getTitle(), message);
	}

	@Test void getProducer(){
		assertEquals("producer", production.getProducer().getName(), message);
	}
	
	@Test void getChannel(){
		assertEquals("TV2 ØST", production.getChannel().getName(),message);
	}
}
