package dk.creditoro.client;

import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * The type Creditoro app.
 */
public class CreditoroApp extends Application {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void start(Stage stage) throws Exception {
        var modelFactory = new ModelFactory();
        var viewModelFactory = new ViewModelFactory(modelFactory);
        var viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start();
    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("Program stopped");
    }
}