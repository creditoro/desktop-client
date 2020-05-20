package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.logging.Logger;

public class AddCreditController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ViewHandler viewHandler;

    @FXML
    private TextField channelNameTxtField;
    @FXML
    private TextField productionTitleTxtField;
    @FXML
    private TextField nameTxtField;
    @FXML
    private TextField jobTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private TextArea creditsTxtArea;


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        AddCreditViewModel addCreditViewModel = viewModelFactory.getAddCreditViewModel();

        addCreditViewModel.setChannelNameTxtField(channelNameTxtField);
        addCreditViewModel.setProductionTitleTxtField(productionTitleTxtField);
        addCreditViewModel.setCreditsTxtArea(creditsTxtArea);
    }

    public void handleSearchBar() {
        viewHandler.openView(Views.FRONTPAGE);
    }

    public void btnChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    public void btnProductions() {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    public void lblStartMenuPressed() {
        viewHandler.openView(Views.FRONTPAGE);
    }

    public void btnAccount() {
        LOGGER.info("Account button pressed.");
    }

    public void addCreditOnAction() {
        String name = nameTxtField.getText();
        String job = jobTxtField.getText();

        creditsTxtArea.appendText("\n" + name + "\t" + job);

        nameTxtField.clear();
        emailTxtField.clear();
        jobTxtField.clear();
        nameTxtField.requestFocus();
        LOGGER.info("Credit added");
    }

    public void deleteOnAction() {
        LOGGER.info("Credit deleted");
    }

    public void deleteAllOnAction() {
        LOGGER.info("All credits deleted");
    }

    public void exportOnAction() {
        LOGGER.info("Exporting credits");
    }

    public void exitOnAction() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.PRODUCTION);
    }
}
