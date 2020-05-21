package dk.creditoro.client.networking.dummy_client;

import dk.creditoro.client.model.crud.*;
import dk.creditoro.client.networking.IClient;

import java.beans.PropertyChangeListener;
import java.util.Map;

public class DummyClient implements IClient {
    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public void register(User user) {
        return;
    }

    @Override
    public Channel[] searchChannels(String q) {
        return new Channel[0];
    }

    @Override
    public Production[] searchProductions(String q) {
        return new Production[0];
    }

    @Override
    public Credit[] getCredits(String q) {
        return new Credit[0];
    }

	@Override
	public Credit patchCredits(String identifier, Map<String, Object> fields) {
		return new Credit("ID-1-1-1-11", 
				new Production("ID-3-3-3", "title-10", "Description LONG", 
					new User("ID-5-5-5", "30303030" , "Mail@Mail2.gg", "ProducerName", "admin"),
					new Channel("id-4-4-4", "DR1", "https://api.creditoro.nymann.dev")), 
				new Person("ID-2-2-2", "505055050", "Mail@mail.gg" , "MYNAMEISNOTHING"), 
				"jobtype");
	}

	@Override
	public Credit postCredits(Credit credit) {
		return new Credit("ID-1-1-1-11", 
				new Production("ID-3-3-3", "title-10", "Description LONG", 
					new User("ID-5-5-5", "30303030" , "Mail@Mail2.gg", "ProducerName", "admin"),
					new Channel("id-4-4-4", "DR1", "https://api.creditoro.nymann.dev")), 
				new Person("ID-2-2-2", "505055050", "Mail@mail.gg" , "MYNAMEISNOTHING"), 
				"jobtype");
	}

	@Override
	public Person[] getPersons(String q) {
		return new Person[0];
	}

    @Override
    public void addListener(String name, PropertyChangeListener propertyChangeListener) {
    }
}
