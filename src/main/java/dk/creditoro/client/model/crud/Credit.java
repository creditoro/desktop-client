package dk.creditoro.client.model.crud;

/**
 * The type Credit.
 */
public class Credit {

    /*
        Read about transient modifier here: https://www.baeldung.com/gson-exclude-fields-serialization
    */
    private final transient String identifier;
    private final Production production;
    private final Person person;
    private final String job;

    /**
     * Instantiates a new Credit.
     *
     * @param identifier the identifier
     * @param production the production
     * @param person     the person
     * @param job        the job
     */
    public Credit(String identifier, Production production, Person person, String job) {
        this.identifier = identifier;
        this.production = production;
        this.person = person;
        this.job = job;
    }

    /**
     * Gets identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets production.
     *
     * @return the production
     */
    public Production getProduction() {
        return production;
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Gets job.
     *
     * @return the job
     */
    public String getJob() {
        return job;
    }
}
