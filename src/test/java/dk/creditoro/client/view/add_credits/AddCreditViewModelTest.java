package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ClientFactory;
import dk.creditoro.client.core.ModelFactory;
import dk.creditoro.client.core.ViewModelFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
        addCreditViewModel.export(exportFileTest);
        assertNotEquals(0, exportFileTest.getTotalSpace());
        addCreditViewModel.export(null);
        addCreditViewModel.importCredits(null);
    }

}