package dk.creditoro.client.model.crud;

public class Credit {
    private final String identifier;
    private final String title;
    private final String description;

    public Credit(String identifier, String title, String description)
    {
        this.identifier = identifier;
        this.title = title;
        this.description = description;
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
}
