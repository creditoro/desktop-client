package dk.creditoro.client.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
* ModelFactoryTest
*/
public class ModelFactoryTest {
	ModelFactory modelFactory;

	public ModelFactoryTest(){
		modelFactory = new ModelFactory(new ClientFactory());
	}
	
	// UserModel
	@Test public void notNullUserModel(){
		assertNotNull(modelFactory.getUserModel());
	}

	@Test public void getUserModel(){
		assertEquals(modelFactory.getUserModel(), modelFactory.getUserModel(), 
				"Check that we will always return the same UserModel");
	}

	// ChannelModel
	@Test public void notNullChannelModel(){
		assertNotNull(modelFactory.getChannelModel());
	}

	@Test public void getChannelModel(){
		assertEquals(modelFactory.getChannelModel(), modelFactory.getChannelModel(), 
				"Check that we will always return the same ChannelModel");
	}
	
	// ChannelModel
	@Test public void notNullProductionModel(){
		assertNotNull(modelFactory.getProductionModel());
	}

	@Test public void getProductionModel(){
		assertEquals(modelFactory.getProductionModel(), modelFactory.getProductionModel(), 
				"Check that we will always return the same ProductionModel");
	}

}
