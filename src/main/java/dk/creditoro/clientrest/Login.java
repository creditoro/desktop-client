package dk.creditoro.clientrest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Login {

    private String token;

    public boolean SignIn(String mail, String password)
    {
        String info = "{\n" + " \"email\": \"" + mail + "\",\n" + " \"password\": \"" + password + "\"\n" + "}";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpHost host = new HttpHost("api.creditoro.nymann.dev", 443, "https");

            //Specify get request
            HttpPost request = new HttpPost("/users/login");

            //String Entity
            StringEntity login = new StringEntity(info, ContentType.APPLICATION_JSON);
            request.setEntity(login);

            //Response
            response = httpClient.execute(host, request);
            HttpEntity entity = response.getEntity();

            //Get JSON
            token = getJSON(streamToString(entity));
            //System.out.println(token);

            //Successful
            return true;

        } catch(IOException e){
            e.printStackTrace();
            //Not so much
            return false;
        } finally{
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getJSON(String response)
    {
        JSONObject tokenString = new JSONObject(response);
        return tokenString.getString("token");
    }

    public String streamToString(HttpEntity response) {
        try {
            return new BufferedReader(new InputStreamReader(response.getContent())).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getToken()
    {
        return token;
    }
}
