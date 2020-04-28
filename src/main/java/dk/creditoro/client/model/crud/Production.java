package dk.creditoro.client.model.crud;

public class Production {
    private String identifier;
    private String title;
    private String producer;
    private String[] channels;

    public Production(String identifier, String title, String[] producers, String[] channels) {
        this.identifier = identifier;
        this.title = title;
        this.producers = producers;
        this.channels = channels;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String[] getProducers() {
        return producers;
    }

    public String[] getChannels() {
        return channels;
    }
}
