package dk.creditoro.client.core;

import dk.creditoro.client.networking.IClient;
import dk.creditoro.client.networking.dummy_client.DummyClient;
import dk.creditoro.client.networking.rest_client.RestClient;

public class ClientFactory {

    private IClient restClient;
    private IClient dummyClient;

    public IClient getRestClient() {
        if(restClient == null) {
            restClient = new RestClient();
        }
        return restClient;
    }

    public IClient getDummyClient() {
        if(dummyClient == null) {
            dummyClient = new DummyClient();
        }
        return dummyClient;
    }

}
