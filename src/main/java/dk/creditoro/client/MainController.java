package dk.creditoro.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML public TextField txt_username;
	@FXML public TextField txt_password;
	@FXML public Button btn_SignIn;

	public MainController()
	{

	}

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle)
	{

	}

	@FXML
	public void SignIn(ActionEvent actionEvent)
	{
		String email = txt_username.getText();
		String password = txt_password.getText();

		App.login.signIn(email, password);

		//Change scene
		try {
			App.setRoot("SearchChannels");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
