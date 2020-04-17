package dk.creditoro.rest_client;

import dk.creditoro.exceptions.HttpStatusException;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * The type Testing stuff fool around.
 */
public class TestingStuffFoolAround {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Login login = new Login();
        try {
            var signedIn = login.signIn("string@string.dk", "string");
            if (signedIn) {
                LOGGER.info("User signed in successfully");
                LOGGER.info(String.format("token: %s", login.getToken()));
            } else {
                LOGGER.info("Wrong email/password");
            }
        } catch (IOException e) {
            LOGGER.info("IO error: " + e);
        } catch (HttpStatusException e) {
            LOGGER.info("Http error: " + e);
        }
        Channels channels = new Channels();

        if (channels.postChannel("DR998", login.getToken())) {
            LOGGER.info("Updated channel name.");
        }
        channels.getChannels();
    }

}

