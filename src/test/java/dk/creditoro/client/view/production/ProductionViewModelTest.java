package dk.creditoro.client.view.production;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.*;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import javafx.embed.swing.JFXPanel;

/**
* ProductionViewModelTest
*/
public class ProductionViewModelTest {
	ProductionViewModel pdViewModel;

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
		ViewModelFactory vmlF = new ViewModelFactory(mlF);
		pdViewModel = vmlF.getProductionViewModel();
	}

	@Test
	void changeQueryParam(){
		pdViewModel.queryParamProperty().setValue("This");
		pdViewModel.setId("This should not be set like this??? ");
		// pdViewModel.getCredits();
	}
}
