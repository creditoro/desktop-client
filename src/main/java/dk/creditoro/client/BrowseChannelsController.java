package dk.creditoro.client;

import dk.creditoro.exceptions.HttpStatusException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseChannelsController implements Initializable {

	public BrowseChannelsController()
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
	}

	public void postChannel(String name) throws IOException, HttpStatusException {
		App.channels.postChannel(name, App.login.getToken());
	}

	public void handleSearchBar(ActionEvent actionEvent)
	{

	}
	public void handleBtnAccount(ActionEvent actionEvent)
	{

	}
	public void handleBtnSearch(MouseEvent mouseEvent)
	{

	}
}
