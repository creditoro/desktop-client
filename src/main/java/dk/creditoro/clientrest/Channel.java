package dk.creditoro.clientrest;

public class Channel {

    private String name;
    private String id;


    public Channel(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }


    public String toString()
    {
        return "identifier: " + id + "\nname: " + name + "\n";
    }

}
