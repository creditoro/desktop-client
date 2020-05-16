package dk.creditoro.client.model.crud;

public class Credit {
    private final String identifier;
    private final Production production;
    private final Person person;
    private final String job;

    public Credit(String identifier, Production production, Person person, String job)
    {
        this.identifier = identifier;
        this.production = production;
        this.person = person;
        this.job = job;
    }


    public String getIdentifier() {
        return identifier;
    }

    public Production getProduction() {
        return production;
    }

    public Person getPerson() {
        return person;
    }

    public String getJob() {
        return job;
    }
}
