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
    private String token;

    public User(String identifier, String phone, String email, String password, String name, String role, String token) {
        this.identifier = identifier;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.token = token;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.name = "";
        this.phone = "";
        this.role = "";
        this.identifier = "";
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
