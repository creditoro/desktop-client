package dk.creditoro.rest_client;

/**
 * The type Channel.
 */
public class Channel {

    private final String name;
    private final String id;


    /**
     * Instantiates a new Channel.
     *
     * @param id   the id
     * @param name the name
     */
    public Channel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    public String toString() {
        return "identifier: " + id + "\nname: " + name + "\n";
    }

}
