package dk.creditoro.client.view.add_credits;

import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.crud.Person;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.UUID;
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
    private TextField phoneTxtField;
    @FXML
    private TextField jobTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private TextArea creditsTxtArea;


    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        addCreditViewModel = viewModelFactory.getAddCreditViewModel();

        // Set productionTitle
        productionTitleTxtField.textProperty().bindBidirectional(addCreditViewModel.productionTitleProperty());

        // Set channelName
        channelNameTxtField.textProperty().bindBidirectional(addCreditViewModel.channelNameProperty());

        // Set credits
        addCreditViewModel.setCreditsTxtArea(creditsTxtArea);
    }

    public void btnAccount() {
        LOGGER.info("Account button pressed.");
    }

    public void addCreditOnAction() {
        String creditIdentifier = UUID.randomUUID().toString();
        String personIdentifier = UUID.randomUUID().toString();
        Production production = addCreditViewModel.getProduction();
        Person person;

        String email = emailTxtField.getText();
        String name = nameTxtField.getText();
        String job = jobTxtField.getText();

        if (addCreditViewModel.getPerson(email) != null){
            person = addCreditViewModel.getPerson(email);

        } else {
            phoneTxtField.setDisable(false);
            phoneTxtField.setPromptText("Tilføj venligst et telefonnummer");
            String phone = phoneTxtField.getText();
            person = new Person(personIdentifier, phone, email, name);
        }

        creditsTxtArea.appendText(name + "\t" + job + "\n");
        nameTxtField.clear();
        emailTxtField.clear();
        jobTxtField.clear();
        nameTxtField.requestFocus();

        addCreditViewModel.setCredit(new Credit(creditIdentifier, production, person, job));
        addCreditViewModel.postCredits();
        LOGGER.info("Credit added");
    }

    public void deleteOnAction() {
        LOGGER.info("Credit deleted");
    }

    public void exitOnAction() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.PRODUCTION);
    }

    public void btnChannels() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    public void btnProductions() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    @FXML
    public void btnFrontPage() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.FRONTPAGE);
    }
}
