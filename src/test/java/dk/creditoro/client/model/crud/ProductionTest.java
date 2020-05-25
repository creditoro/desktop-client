package dk.creditoro.client.model.crud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ProductionTest
 */
class ProductionTest {
    Production production;
    String message = "Maybe production changed?";

    public ProductionTest() {
        var producer = new User("madeUpIdentifier", "phone", "email", "producer", "producer");
        var channel = new Channel("madeUpIdentifier", "TV2 ØST", "iconUrl");
        production = new Production("10-10-10", "Hornbœk langt ude","Og det var Hornbæk der var langt ude", producer, channel);

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
        assertEquals("Og det var Hornbæk der var langt ude", production.getDescription(), message);
    }

    @Test
    void getProducer() {
        assertEquals("producer", production.getProducer().getName(), message);
    }

    @Test
    void getChannel() {
        assertEquals("TV2 ØST", production.getChannel().getName(), message);
    }
}
