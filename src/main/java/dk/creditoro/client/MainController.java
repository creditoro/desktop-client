package dk.creditoro.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dk.creditoro.exceptions.HttpStatusException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML public TextField txtUsername;
	@FXML public TextField txtPassword;
	@FXML public Button btnSignIn;


	@Override
    public void initialize(URL url, ResourceBundle resourceBundle)
	{
		//TODO Setup code here
	}

	@FXML
	public void SignIn(ActionEvent actionEvent)
	{
		String email = txtUsername.getText();
		String password = txtPassword.getText();

		try {
			App.login.signIn(email, password);
		} catch (IOException | HttpStatusException e) {
			//TODO Add Logger
			e.printStackTrace();
		}

		//Change scene
		try {
			App.setRoot("SearchChannels");
		} catch (IOException e) {
			//TODO Add Logger
			e.printStackTrace();
		}
	}
}
