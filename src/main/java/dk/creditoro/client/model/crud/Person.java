package dk.creditoro.client.model.crud;

public class Person {

    /*
        Read about transient modifier here: https://www.baeldung.com/gson-exclude-fields-serialization
    */
    private final transient String identifier;
    private final String phone;
    private final String email;
    private final String name;

    public Person(String identifier, String phone, String email, String name) {
        this.identifier = identifier;
        this.phone = phone;
        this.email = email;
        this.name = name;
    }


    public String getIdentifier() {
        return identifier;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
