package dk.creditoro.client.model.crud;

import com.google.gson.annotations.SerializedName;

/**
 * The type Credit.
 */
public class Credit {

    private final String identifier;
    private final Production production;
    private final Person person;
    private final String job;
    @SerializedName("production_id")
    private final String productionId;
    @SerializedName("person_id")
    private final String personId;

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
        this.productionId = null;
        this.personId = null;
    }

    /**
     * Instantiates a new Credit.
     *
     * @param productionId the production id
     * @param personId     the person id
     * @param job          the job
     */
    public Credit(String productionId, String personId, String job) {
        this.identifier = null;
        this.production = null;
        this.person = null;
        this.productionId = productionId;
        this.personId = personId;
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

    /**
     * Gets production id.
     *
     * @return the production id
     */
    public String getProductionId() {
        return productionId;
    }

    /**
     * Gets person id.
     *
     * @return the person id
     */
    public String getPersonId() {
        return personId;
    }
}
