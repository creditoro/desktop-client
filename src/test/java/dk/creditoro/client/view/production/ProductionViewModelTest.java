package dk.creditoro.client.view.production;

import org.junit.jupiter.api.*;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;

/**
* ProductionViewModelTest
*/
public class ProductionViewModelTest {
	ProductionViewModel pdViewModel;

	public ProductionViewModelTest() {
		ModelFactory mlF = new ModelFactory(new ClientFactory());
		ViewModelFactory vmlF = new ViewModelFactory(mlF);
		pdViewModel = vmlF.getProductionViewModel();
	}

	@Test
	void changeQueryParam(){
		pdViewModel.queryParamProperty().setValue("This");
		pdViewModel.getCredits();
	}
}
