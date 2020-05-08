package dk.creditoro.client.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
* ViewModelFactoryTest
*/
public class ViewModelFactoryTest {
	ViewModelFactory viewModelFactory;
	
	public ViewModelFactoryTest(){
		viewModelFactory = new ViewModelFactory(new ModelFactory(new ClientFactory()));
	}

	@Test void getLoginViewModel(){
		assertNotNull(viewModelFactory.getLoginViewModel());
	}

	@Test void getBrowseChannelsViewModel(){
		assertNotNull(viewModelFactory.getBrowseChannelsViewModel());
	}
}
