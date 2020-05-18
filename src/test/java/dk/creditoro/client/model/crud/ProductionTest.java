package dk.creditoro.client.model.crud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ProductionTest
 */
public class ProductionTest {
    Production production;
    String message = "Maybe production changed?";

    public ProductionTest() {
        var producer = new User("madeUpIdentifier", "phone", "email", "producer", "producer");
        var channel = new Channel("madeUpIdentifier", "TV2 ØST", "iconUrl");
        var category = new String[]{"test"};
        production = new Production("10-10-10", "Hornbœk langt ude","Og det var Hornbæk der var langt ude", category, producer, channel);

    }

    @Test
    void getIdentifier() {
        assertEquals("10-10-10", production.getIdentifier(), message);
    }

    @Test
    void getTitle() {
        assertEquals("Hornbœk langt ude", production.getTitle(), message);
    }

    @Test
    void getDescription() {
        assertEquals("Her er en beskrivelse", production.getDescription(), message);
    }

    @Test
    void getCategory() { assertEquals("car", production.getCategory(), message);}

    @Test
    void getProducer() {
        assertEquals("producer", production.getProducer().getName(), message);
    }

    @Test
    void getChannel() {
        assertEquals("TV2 ØST", production.getChannel().getName(), message);
    }
}