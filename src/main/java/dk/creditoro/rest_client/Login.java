package dk.creditoro.rest_client;

import dk.creditoro.exceptions.HttpStatusException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * The type Login.
 */
public class Login {

    private String token;

    /**
     * Sign in boolean.
     *
     * @param mail     the mail
     * @param password the password
     * @return the boolean
     * @throws IOException         the io exception
     * @throws HttpStatusException the http status exception
     */
    public boolean signIn(String mail, String password) throws IOException, HttpStatusException {
        HttpManager httpManager = new HttpManager("api.creditoro.nymann.dev", 443, "https");
        JSONObject userLogin = new JSONObject();
        userLogin.put("email", mail);
        userLogin.put("password", password);
        String response = httpManager.post("/users/login", userLogin);
        token = convertResponseToTokenString(response);
        return true;
    }

    /**
     * Convert response to token string string.
     *
     * @param response the response
     * @return the string
     */
    public String convertResponseToTokenString(String response) {
        JSONObject tokenString = new JSONObject(response);
        return tokenString.getString("token");
    }


    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }
}
