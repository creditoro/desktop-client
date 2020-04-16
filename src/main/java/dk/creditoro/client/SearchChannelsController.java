package dk.creditoro.client;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchChannelsController implements Initializable {

	public SearchChannelsController()
	{

	}

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle)
	{
		try {
			getChannel("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Call with "" - string for all channels
	public void getChannel(String q) throws IOException {
		App.channels.getChannels(q);

		//Display channels

	}

	public void postChannel(String name) throws IOException {
		App.channels.postChannel(name, App.login.getToken());
	}


	public void displayChannels()
	{

	}
}
