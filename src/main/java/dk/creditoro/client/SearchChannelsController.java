package dk.creditoro.client;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
	public void getChannel(String q) {
		App.channels.getChannels(q);
	}

	public void postChannel(String name) {
		App.channels.postChannel(name, App.login.getToken());
	}
}
