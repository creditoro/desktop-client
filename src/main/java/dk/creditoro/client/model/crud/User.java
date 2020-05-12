package dk.creditoro.client.model.crud;

public class User {
    private final String identifier;
    private final String phone;
    private final String email;
    private final String name;
    private final String role;

    public User(String identifier, String phone, String email, String name, String role) {
        this.identifier = identifier;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public User(String phone, String email, String name, String role) {
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.role = role;
        this.identifier = null;
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

    public String getRole() {
        return role;
    }
}
