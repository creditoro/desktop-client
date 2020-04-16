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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HttpManager {

    private String hostURL;
    private int port;
    private String scheme;

    public HttpManager(String host, int port, String scheme)
    {
        this.hostURL = host;
        this.port = port;
        this.scheme = scheme;
    }

    public String get(String path, String query) throws IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpHost host = new HttpHost(hostURL, port, scheme);

            //Specify get request
            HttpGet req = new HttpGet(path + "?=" + query);

            //Response
            response = httpClient.execute(host, req);
            HttpEntity entity = response.getEntity();

            //JSON to String
            return streamToString(entity);

        } finally{
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String get(String path, String query, String token) throws IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpHost host = new HttpHost(hostURL, port, scheme);

            //Specify get request
            HttpGet p = new HttpGet(path + "?=" + query);

            //Add Token
            p.addHeader(new BasicHeader("Authorization", token));

            //Response
            response = httpClient.execute(host, p);
            HttpEntity entity = response.getEntity();

            //JSON to String
            return streamToString(entity);

        } finally{
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String post(String path, JSONObject json, String token) throws IOException
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpHost host = new HttpHost(hostURL, port, scheme);

            //Specify post request
            HttpPost post = new HttpPost(path);

            //Add Token
            post.addHeader(new BasicHeader("Authorization", token));

            //String Entity
            StringEntity postEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
            post.setEntity(postEntity);

            //System.out.println(Arrays.toString(post.getAllHeaders()));

            //Response
            response = httpclient.execute(host, post);
            HttpEntity entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status code: " + statusCode);

            if(statusCode != 201)
            {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            return streamToString(entity);

        } finally{
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String post(String path, JSONObject json) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpHost host = new HttpHost(hostURL, port, scheme);

            //Specify post request
            HttpPost post = new HttpPost(path);

            //String Entity
            StringEntity postEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
            post.setEntity(postEntity);

            //System.out.println(Arrays.toString(post.getAllHeaders()));

            //Response
            response = httpclient.execute(host, post);
            HttpEntity entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status code: " + statusCode);

            if(statusCode != 201)
            {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            return streamToString(entity);

        } finally{
            try {
                response.close();
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
