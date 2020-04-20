package dk.creditoro.client.model.crud;

import com.google.gson.annotations.SerializedName;

public class Channel {
    private final String identifier;
    private final String name;

    @SerializedName("icon_url")
    private final String iconUrl;

    public Channel(String identifier, String name, String iconUrl) {
        this.identifier = identifier;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
