package dk.creditoro.client;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import dk.creditoro.client.core.CreditoroApp;
import javafx.application.Application;

/**
 * The type Main.
 */
public class RunCreditoroApp {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SvgImageLoaderFactory.install();
        Application.launch(CreditoroApp.class);
    }
}
