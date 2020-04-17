package dk.creditoro.rest_client;

public class Channel {

    private final String name;
    private final String id;


    public Channel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String toString() {
        return "identifier: " + id + "\nname: " + name + "\n";
    }

}
