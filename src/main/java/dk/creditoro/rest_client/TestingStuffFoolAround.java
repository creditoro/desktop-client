package dk.creditoro.rest_client;

import dk.creditoro.exceptions.HttpStatusException;

import java.io.IOException;

/**
 * The type Testing stuff fool around.
 */
public class TestingStuffFoolAround {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Login login = new Login();
        try {
            System.out.println(login.signIn("string@string.dk", "string"));
            System.out.println("token:" + login.getToken());
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        } catch (HttpStatusException e) {
            System.out.println("Http error: " + e);
        }
        Channels channels = new Channels();

        String name = "DR998";
        System.out.println("New Channel name: " + name);
        System.out.println(channels.postChannel(name, login.getToken()));
        channels.getChannels();
    }

}

