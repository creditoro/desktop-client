package dk.creditoro.client.model.crud;

public class Production {
    private String identifier;
    private String title;
    private String producer;
    private String[] channels;

    public Production(String identifier, String title, String producer, String[] channels) {
        this.identifier = identifier;
        this.title = title;
        this.producer = producer;
        this.channels = channels;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getProducer() {
        return producer;
    }

    public String[] getChannels() {
        return channels;
    }
}
