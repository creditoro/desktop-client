package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewHandler;
import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.core.Views;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.crud.Person;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.view.IViewController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * The type Add credit controller.
 */
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

    /**
     * Btn account.
     */
    public void btnAccount() {
        LOGGER.info("Account button pressed.");
    }

    /**
     * Add credit on action.
     */
    public void addCreditOnAction() {
        String creditIdentifier = UUID.randomUUID().toString();
        Production production = addCreditViewModel.getProduction();
        String personIdentifier;
        Person person;

        String email = emailTxtField.getText();
        String name = nameTxtField.getText();
        String job = jobTxtField.getText();
        String phone;

        if (phoneTxtField.isDisabled()) {
            if (addCreditViewModel.getPerson(email) == null) {
                createPopup("Personen blev ikke fundet", "Tilføj venligst et telefonnummer for at oprette personen", 5, Pos.BASELINE_CENTER);
                phoneTxtField.setDisable(false);
                phoneTxtField.requestFocus();

            } else {
                person = addCreditViewModel.getPerson(email);
                creditsTxtArea.appendText(name + "\t" + job + "\n");

                clearFields();
                nameTxtField.requestFocus();
                postCredit(creditIdentifier, production, person, job);
            }
        } else {
            phone = phoneTxtField.getText();
            personIdentifier = UUID.randomUUID().toString();
            creditIdentifier = UUID.randomUUID().toString();
            person = new Person(personIdentifier, phone, email, name);
            postPerson(person);
            postCredit(creditIdentifier, production, person, job);
            phoneTxtField.setDisable(true);
            creditsTxtArea.appendText(name + "\t" + job + "\n");
            clearFields();
        }
    }

    private void postPerson(Person person) {
        addCreditViewModel.setPerson(person);
        addCreditViewModel.postPerson();
    }

    private void clearFields() {
        nameTxtField.clear();
        emailTxtField.clear();
        phoneTxtField.clear();
        jobTxtField.clear();
    }

    private void postCredit(String creditIdentifier, Production production, Person person, String job) {
        addCreditViewModel.setCredit(new Credit(creditIdentifier, production, person, job));
        addCreditViewModel.postCredits();
        LOGGER.info("Credit added");
    }

    /**
     * Delete on action.
     */
    public void deleteOnAction() {
        LOGGER.info("Credit deleted");
    }

    /**
     * Exit on action.
     */
    public void exitOnAction() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.PRODUCTION);
    }

    /**
     * Btn channels.
     */
    public void btnChannels() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.BROWSE_CHANNELS);
    }

    /**
     * Btn productions.
     */
    public void btnProductions() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.BROWSE_PRODUCTIONS);
    }

    /**
     * Btn front page.
     */
    @FXML
    public void btnFrontPage() {
        creditsTxtArea.clear();
        viewHandler.openView(Views.FRONTPAGE);
    }

    /**
     * Create popup.
     *
     * @param title    the title
     * @param text     the text
     * @param duration the duration
     * @param position the position
     */
    public void createPopup(String title, String text, int duration, Pos position) {
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(duration))
                .position(position)
                .onAction(actionEvent -> LOGGER.info("Pressed popup"));
        notificationBuilder.show();
    }

    /**
     * Export on action.
     */
    public void exportOnAction() {
        LOGGER.info("Eksporterer krediteringer");
    }

    /**
     * Import on action.
     */
    public void importOnAction() {
        LOGGER.info("Importerer krediteringer");
    }
}
