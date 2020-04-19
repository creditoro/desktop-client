package dk.creditoro.client.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewHandlerTest {
    private ViewHandler viewHandler;

    public ViewHandlerTest() {
        var clientFactory = new ClientFactory();
        var modelFactory = new ModelFactory(clientFactory);
        var viewModelFactory = new ViewModelFactory(modelFactory);
        viewHandler = new ViewHandler(viewModelFactory);

    }

    @Test
    void start() {
    }

    @Test
    void openView() {
    }
}