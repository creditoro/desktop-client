package dk.creditoro.client.model.crud;

public class User {
    public String getIdentifier() {
        return identifier;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    private final String identifier;
    private final String phone;
    private final String email;
    private final String password;
    private final String name;
    private final String role;

    public User(String identifier, String phone, String email, String password, String name, String role) {
        this.identifier = identifier;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
