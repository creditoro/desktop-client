package dk.creditoro.client.model.crud;


public class Production {

    private final String identifier;
    private final String title;
    private final String description;
    private final String[] category;
    private final User producer;
    private final Channel channel;


    public Production(String identifier, String title, String description, String[] category, User producer, Channel channel) {
        this.identifier = identifier;
        this.title = title;
        this.description = description;
        this.category = category;
        this.producer = producer;
        this.channel = channel;

    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String[] getCategory() {
        return category;
    }

    public User getProducer() {
        return producer;
    }

    public Channel getChannel() {
        return channel;
    }


}
