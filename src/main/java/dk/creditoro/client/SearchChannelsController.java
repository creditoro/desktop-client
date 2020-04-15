package dk.creditoro.client;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
			HttpGet request = new HttpGet("/channels/?q=" + q);

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
}
