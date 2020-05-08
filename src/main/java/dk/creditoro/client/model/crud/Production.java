package dk.creditoro.client.model.crud;

public class Production {
    private String identifier;
    private String title;
    private User producer;
    private Channel channel;

    public Production(String identifier, String title, User producer, Channel channel) {
        this.identifier = identifier;
        this.title = title;
        this.producer = producer;
        this.channel = channel;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public User getProducer() {
        return producer;
    }

    public Channel getChannel() {
        return channel;
    }
}
