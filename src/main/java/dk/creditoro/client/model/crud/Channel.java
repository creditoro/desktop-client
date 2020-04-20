package dk.creditoro.client.model.crud;

public class Channel {
    private final String identifier;
    private final String name;

    public Channel(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }
}
