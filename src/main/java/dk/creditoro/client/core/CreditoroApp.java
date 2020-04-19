package dk.creditoro.client.core;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * The type Creditoro app.
 */
public class CreditoroApp extends Application {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void start(Stage stage) {
        var clientFactory = new ClientFactory();
        var modelFactory = new ModelFactory(clientFactory);
        var viewModelFactory = new ViewModelFactory(modelFactory);
        var viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start();
    }

    @Override
    public void stop() {
        LOGGER.info("Program stopped");
    }
}