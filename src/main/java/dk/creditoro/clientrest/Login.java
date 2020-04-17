package dk.creditoro.clientrest;

import dk.creditoro.exceptions.HttpStatusException;
import org.json.JSONObject;

import java.io.IOException;

public class Login {

    private String token;

    // TODO exceptionhandling
    public boolean signIn(String mail, String password) throws IOException, HttpStatusException {
        HttpManager httpManager = new HttpManager("api.creditoro.nymann.dev",443,"https");
        JSONObject userLogin = new JSONObject();
        userLogin.put("email",mail);
        userLogin.put("password",password);
        String response = httpManager.post("/users/login",userLogin);
		token = convertResponseToTokenString(response);
        return true;
    }

    public String convertResponseToTokenString(String response)
    {
        JSONObject tokenString = new JSONObject(response);
        return tokenString.getString("token");
    }


    public String getToken()
    {
        return token;
    }
}
