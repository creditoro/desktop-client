package dk.creditoro.client;

import dk.creditoro.rest_client.Channels;
import dk.creditoro.rest_client.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    /**
     * The constant login.
     */
    public static Login login = new Login();
    /**
     * The constant channels.
     */
    public static Channels channels = new Channels();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginPage"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets root.
     *
     * @param fxml the fxml
     * @throws IOException the io exception
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Launch fx.
     *
     * @param args the args
     */
    public static void launchFX(String[] args) {
        launch(args);
    }

}
