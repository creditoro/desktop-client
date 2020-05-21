package dk.creditoro.client.view.production;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.*;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ProductionViewModelTest
 */
class ProductionViewModelTest {
	ProductionViewModel pdViewModel;
	ViewModelFactory vmlF;

	public ProductionViewModelTest() {
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JFXPanel(); // initializes JavaFX environment
				latch.countDown();
			}
		});
		try{
			latch.await();
		} catch(InterruptedException e){
		}

		//Makes the client that is used to connect to the internet
		ModelFactory mlF = new ModelFactory(new ClientFactory());
		vmlF = new ViewModelFactory(mlF);
		pdViewModel = vmlF.getProductionViewModel();
	}

	@Test
	void changeQueryParam(){
		assertDoesNotThrow(() -> pdViewModel.queryParamProperty().setValue(""));
		assertDoesNotThrow(() -> pdViewModel.setId("40b656a3-15fa-402b-9444-c7f3672a16a3"));
		assertDoesNotThrow(() -> pdViewModel.getCredits());

		// Test the Credits
		while(pdViewModel.listPropertyProperty().isEmpty()){
			//Do nothing waiting on result
		}
		var creditsList = pdViewModel.listPropertyProperty();
		assertNotNull(creditsList.get(0));
		assertNotNull(creditsList.get(0).getIdentifier());
		assertNotNull(creditsList.get(0).getJob());
		assertNotNull(creditsList.get(0).getPerson());
		// assertNotNull(creditsList.get(0).getProduction()); //This test should be turned on, then the API gets fixed

		// Search in the stored credits
		var job = "Lydmand";
		pdViewModel.queryParamProperty().set(job);

		assertDoesNotThrow(() ->  pdViewModel.search());
		System.out.println("\n\n\n\n LENGHT:" + creditsList.size() );
		creditsList = pdViewModel.listPropertyProperty();
		assertEquals(job, creditsList.get(0).getJob(),
				"Maybe credits changed?");
	}

	@Test
	void getAndSetIds(){
		//Get set ID
		assertDoesNotThrow(()-> pdViewModel.setId("thisId"));
		assertEquals("thisId", pdViewModel.getId());
	
		//Get set channelId
		assertDoesNotThrow(()-> pdViewModel.setChannelId("channelId"));
		assertEquals("channelId", pdViewModel.getChannelId());
		
	}

}
