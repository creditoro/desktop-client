package dk.creditoro.client.core;

import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.rest_client.RestClient;

public class ClientFactory {

    private IClient client;

    public IClient getClient() {
        if(client == null) {
            client = new RestClient();
        }
        return client;
    }

}
