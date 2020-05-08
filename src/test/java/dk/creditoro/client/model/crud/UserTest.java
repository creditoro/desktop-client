package dk.creditoro.client.model.crud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * UserTest
 */
public class UserTest {
    String message = "Something in Users.java changed?";
    User user;

    public UserTest() {
        user = new User("10-10-10", "50505050", "string@string.dk",
                "MyName", "My Admin Role");
    }


    @Test
    void testGetMethodForUsers() {
        assertEquals("10-10-10", user.getIdentifier(), message);
        assertEquals("50505050", user.getPhone(), message);
        assertEquals("string@string.dk", user.getEmail(), message);
        assertEquals("MyName", user.getName(), message);
        assertEquals("My Admin Role", user.getRole(), message);
    }

}
