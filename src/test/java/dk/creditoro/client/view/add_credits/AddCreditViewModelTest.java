package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.crud.Production;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddCreditViewModelTest {

    AddCreditViewModel addCreditViewModel;
    File exportFileTest = new File("exportTestFile.txt");
    File importFileTest = new File("importTestFile.txt");

    public AddCreditViewModelTest() {
        var clientFactory = new ClientFactory();
        var modelFactory = new ModelFactory(clientFactory);
        var viewModelFactory = new ViewModelFactory(modelFactory);
        addCreditViewModel = viewModelFactory.getAddCreditViewModel();
        addCreditViewModel.setProductionTitle("Test-Production");
        viewModelFactory.getBrowseChannelsViewModel().search();
        viewModelFactory.getBrowseChannelProductionsViewModel().setChannelName("DR1");
        viewModelFactory.getBrowseChannelProductionsViewModel().createProductionMap();
        viewModelFactory.getBrowseChannelProductionsViewModel().queryParamProperty().setValue("Test-Production");
        Production production = viewModelFactory.getBrowseChannelProductionsViewModel().qSearch().get(0);
        addCreditViewModel.setProduction(production);
        viewModelFactory.getLoginViewModel().emailProperty().set("string@string.dk");
        viewModelFactory.getLoginViewModel().passwordProperty().set("string");
        viewModelFactory.getLoginViewModel().login();
        addCreditViewModel.getPeople();
        if (exportFileTest.exists() && exportFileTest.isFile()) {
            exportFileTest.delete();
        }
        try {
            exportFileTest.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void importAndExportCredits() {
        addCreditViewModel.importCredits(importFileTest);
        assertNotEquals(0, addCreditViewModel.getCredits().size());
        addCreditViewModel.finishCredits(null);
        addCreditViewModel.export(exportFileTest);
        assertNotEquals(0, exportFileTest.getTotalSpace());
        addCreditViewModel.export(null);
        addCreditViewModel.importCredits(new File("randomfile101"));
        addCreditViewModel.getPersonByName("test");
    }

    @Test
    void testOfGetAndSet() {
        addCreditViewModel.importCredits(importFileTest);
        assertNotNull(addCreditViewModel.getCredits());
        assertNotNull(addCreditViewModel.getCreatedCredits());
        assertNotNull(addCreditViewModel.getProduction());
        addCreditViewModel.setChannelName("DR1");
        assertEquals("DR1",addCreditViewModel.getChannelName());
        assertEquals("Test-Production",addCreditViewModel.getProductionTitle());
        assertEquals("Test-Production",addCreditViewModel.productionTitleProperty().getValue());
        assertEquals("DR1",addCreditViewModel.channelNameProperty().getValue());
    }
}