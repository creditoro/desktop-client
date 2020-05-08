package dk.creditoro.client.model.crud;

public class Login {
    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Login(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
