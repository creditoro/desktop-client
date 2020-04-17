package dk.creditoro.clientrest;

import dk.creditoro.exceptions.HttpStatusException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
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
	private HttpHost host;

    public HttpManager(String hostname, int port, String scheme)
    {
        host = new HttpHost(hostname, port, scheme);
    }

    public String get(String path, String query) 
    {
		//Specify get request
		HttpGet request = new HttpGet(path + query);

		// execute the Http request and return the string value from the httpEntity
		return ExecuteHttp(request);
    }

    public String get(String path, String query, String token) 
	{
		//Specify get request
		HttpGet get  = new HttpGet(path + query);

		//Add Token
		get.addHeader(new BasicHeader("Authorization", token));

		// execute the Http request and return the string value from the httpEntity
		return ExecuteHttp(get);
	}

    public String post(String path, JSONObject json, String token) 
	{
		//Specify post request
		HttpPost request = new HttpPost(path);

		//Add Token
		request.addHeader(new BasicHeader("Authorization", token));
		//String Entity
		StringEntity postEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
		request.setEntity(postEntity);

		// execute the Http request and return the string value from the httpEntity
		return ExecuteHttp(request);
    }

    public String post(String path, JSONObject json) 
	{
		//Specify post request
		HttpPost request = new HttpPost(path);

		//String Entity
		StringEntity postEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);

		// Get all information together
		request.setEntity(postEntity);

		// execute the Http request and return the string value from the httpEntity
		return ExecuteHttp(request);
    }


	// Executes the request
	public String ExecuteHttp(HttpUriRequest request)
	{
		try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(host, request)) {
			// Execute Http
				int statusCode = response.getStatusLine().getStatusCode();
				// LOG statusCode
				if(statusCode < 200 || statusCode > 299) {
					throw new HttpStatusException("Failed with HTTP error code : " + statusCode);
				}
				return streamToString(response.getEntity());

		} catch (IOException | HttpStatusException  e) {
			//TODO add LOGGER
		}
		return null;
	}

    public String streamToString(HttpEntity response)
	{
		try {
			return new BufferedReader(new InputStreamReader(response.getContent())).lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			// TODO use logger here 
			return null;
		}
	}
}
