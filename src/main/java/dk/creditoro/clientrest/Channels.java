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
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Channels {

    public boolean postChannel(String name, String token)
    {
        final String postParams = "{\"name\":\"" + name +  "\" }";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        Scanner scanner = null;
        CloseableHttpResponse response = null;
        try {
            HttpHost host = new HttpHost("api.creditoro.nymann.dev", 443, "https");

            //Specify post request
            HttpPost post = new HttpPost("/channels/");

            //Add Token
            post.addHeader(new BasicHeader("Authorization", token));

            //String Entity
            StringEntity postEntity = new StringEntity(postParams, ContentType.APPLICATION_JSON);
            post.setEntity(postEntity);

            System.out.println(Arrays.toString(post.getAllHeaders()));

            //Response
            response = httpclient.execute(host, post);
            HttpEntity entity = response.getEntity();

            if(entity == null)
            {
                System.out.println("Entity is null");
            } else{
                scanner = new Scanner(entity.getContent());

                while(scanner.hasNext())
                {
                    System.out.println(scanner.next());
                }
            }

            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status code: " + statusCode);

            if(statusCode != 201)
            {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            //Successfully added channel
            return true;

        } catch(Exception e){
            e.printStackTrace();

            //Didn't add channel
            return false;
        } finally{
            scanner.close();
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getChannels(String q)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpHost host = new HttpHost("api.creditoro.nymann.dev", 443, "https");

            //Specify get request
            HttpGet request = new HttpGet("/channels/?q=" + q);

            //Response
            response = httpClient.execute(host, request);
            HttpEntity entity = response.getEntity();

            //Get List From JSON
            ArrayList<Channel> channelList = getList(streamToString(entity));
            System.out.println(channelList);


        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public ArrayList<Channel> getList(String response)
    {
        JSONArray channels = new JSONArray(response);
        ArrayList<Channel> temp = new ArrayList<Channel>();

        for(int i = 0; i < channels.length(); i++)
        {
            JSONObject channel = channels.getJSONObject(i);
            String identifier = channel.getString("identifier");
            String name = channel.getString("name");

            temp.add(new Channel(identifier, name));
        }

        return temp;
    }

    public String streamToString(HttpEntity response) {
        try {
            String content = new BufferedReader(new InputStreamReader(response.getContent())).lines().collect(Collectors.joining("\n"));
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
