package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddCreditViewModelTest {

    AddCreditViewModel addCreditViewModel;

    public AddCreditViewModelTest() {
        var clientFactory = new ClientFactory();
        var modelFactory = new ModelFactory(clientFactory);
        var viewModelFactory = new ViewModelFactory(modelFactory);
        addCreditViewModel = viewModelFactory.getAddCreditViewModel();
    }

}