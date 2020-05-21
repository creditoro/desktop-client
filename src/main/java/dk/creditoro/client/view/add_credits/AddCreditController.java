package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.view.IViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.logging.Logger;

public class AddCreditController implements IViewController {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ViewHandler viewHandler;
    private AddCreditViewModel addCreditViewModel;

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
    @FXML
    private Button btnAccount;


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.addCreditViewModel = viewModelFactory.getAddCreditViewModel();

        // Set productionTitle
        productionTitleTxtField.textProperty().bindBidirectional(addCreditViewModel.productionTitleProperty());

        // Set credits
//        creditsTxtArea.textProperty().bindBidirectional(addCreditViewModel.getCredits());
        addCreditViewModel.setCreditsTxtArea(creditsTxtArea);
    }

    public void btnAccount() {
        LOGGER.info("Account button pressed.");
    }

    public void addCreditOnAction() {
        String name = nameTxtField.getText();
        String job = jobTxtField.getText();

        creditsTxtArea.appendText(name + "\t" + job + "\n");

        nameTxtField.clear();
        emailTxtField.clear();
        jobTxtField.clear();
        nameTxtField.requestFocus();
        LOGGER.info("Credit added");
    }

    public void deleteOnAction() {
        LOGGER.info("Credit deleted");
    }

    public void exitOnAction() {
        viewHandler.openView(Views.PRODUCTION);
    }

    public void btnChannels() {
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    public void btnProductions() {
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    @FXML
    public void btnFrontPage(MouseEvent mouseEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }

    @FXML
    public void btnSearch(ActionEvent actionEvent) {
        viewHandler.openView(Views.FRONTPAGE);
    }
}
