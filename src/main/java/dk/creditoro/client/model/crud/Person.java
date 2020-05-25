package dk.creditoro.client.model.crud;

/**
 * The type Person.
 */
public class Person {

    private final String identifier;
    private final String phone;
    private final String email;
    private final String name;

    /**
     * Instantiates a new Person.
     *
     * @param identifier the identifier
     * @param phone      the phone
     * @param email      the email
     * @param name       the name
     */
    public Person(String identifier, String phone, String email, String name) {
        this.identifier = identifier;
        this.phone = phone;
        this.email = email;
        this.name = name;
    }

    /**
     * Instantiates a new Person.
     *
     * @param phone the phone
     * @param email the email
     * @param name  the name
     */
    public Person(String phone, String email, String name) {
        this.identifier = null;
        this.phone = phone;
        this.email = email;
        this.name = name;
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
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
