package dk.creditoro.client.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
* ModelFactoryTest
*/
class ModelFactoryTest {
	ModelFactory modelFactory;

	public ModelFactoryTest(){
		modelFactory = new ModelFactory(new ClientFactory());
	}
	
	// UserModel
	@Test void notNullUserModel(){
		assertNotNull(modelFactory.getUserModel());
	}

	@Test void getUserModel(){
		assertEquals(modelFactory.getUserModel(), modelFactory.getUserModel(), 
				"Check that we will always return the same UserModel");
	}

	// ChannelModel
	@Test void notNullChannelModel(){
		assertNotNull(modelFactory.getChannelModel());
	}

	@Test void getChannelModel(){
		assertEquals(modelFactory.getChannelModel(), modelFactory.getChannelModel(), 
				"Check that we will always return the same ChannelModel");
	}
	
	// ChannelModel
	@Test void notNullProductionModel(){
		assertNotNull(modelFactory.getProductionModel());
	}

	@Test void getProductionModel(){
		assertEquals(modelFactory.getProductionModel(), modelFactory.getProductionModel(), 
				"Check that we will always return the same ProductionModel");
	}

}
