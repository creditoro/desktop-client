package dk.creditoro.client;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

public class SearchChannelsController implements Initializable {

	public SearchChannelsController()
	{

	}

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle)
	{
		getChannel("");
	}

	//Call with "" - string for all channels
	public void getChannel(String q)
	{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		Scanner scanner = null;
		try {
			HttpHost host = new HttpHost("api.creditoro.nymann.dev", 443, "https");

			//Specify get request
			HttpGet request = new HttpGet("/channels/?q=");

			//Response
			HttpResponse response = httpClient.execute(host, request);
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

		} catch(Exception e){
			e.printStackTrace();
		} finally{
			scanner.close();
			httpClient.getConnectionManager().shutdown();
		}
	}

	public void postChannel(String name)
	{
		final String postParams = "{\"name\":\"" + name +  "\" }";

		DefaultHttpClient httpClient = new DefaultHttpClient();
		Scanner scanner = null;
		try {
			HttpHost host = new HttpHost("api.creditoro.nymann.dev", 443, "https");

			//Specify post request
			HttpPost post = new HttpPost("/channels/");

			//Add Token
			post.addHeader(new BasicHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Impha3JhMTlAc3R1ZGVudC5zZHUuZGsiLCJleHAiOjE1ODcwMjk0MDl9.-1ogQflT7YuwzAhKBxIQMb2o3_T1B2i5z9DRYeRtwDU"));

			//String Entity
			StringEntity postEntity = new StringEntity(postParams, ContentType.APPLICATION_JSON);
			post.setEntity(postEntity);

			System.out.println(Arrays.toString(post.getAllHeaders()));

			//Response
			HttpResponse response = httpClient.execute(host, post);
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

		} catch(Exception e){
			e.printStackTrace();
		} finally{
			scanner.close();
			httpClient.getConnectionManager().shutdown();
		}
	}
}
