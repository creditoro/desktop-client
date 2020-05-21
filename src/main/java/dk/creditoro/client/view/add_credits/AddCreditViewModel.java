package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Credit;
import dk.creditoro.client.model.crud.Person;
import dk.creditoro.client.model.crud.Production;
import dk.creditoro.client.model.person.IPersonModel;
import dk.creditoro.client.model.user.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.logging.Logger;

/**
 * The type Add credit view model.
 */
public class AddCreditViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ICreditModel creditModel;
    private final IPersonModel personModel;

    private final StringProperty productionTitle = new SimpleStringProperty();
    private final StringProperty channelName = new SimpleStringProperty();
    private final ObservableList<Credit> creditList = FXCollections.observableArrayList();
    private final ListProperty<Credit> credits = new SimpleListProperty<>(creditList);

    private final ObservableList<Person> personList = FXCollections.observableArrayList();
    private final ListProperty<Person> persons = new SimpleListProperty<>(personList);

    @FXML
    private TextArea creditsTxtArea;

    private Credit credit;
    private Production production;
    private Person person;

    /**
     * Instantiates a new Add credit view model.
     *
     * @param personModel      the person model
     * @param creditModel      the credit model
     * @param userModel        the user model
     * @param viewModelFactory the view model factory
     */
    public AddCreditViewModel(IPersonModel personModel, ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
        this.personModel = personModel;
        this.creditModel = creditModel;
    }

    /**
     * Gets production title.
     *
     * @return the production title
     */
    public String getProductionTitle() {
        return productionTitle.get();
    }

    /**
     * Sets production title.
     *
     * @param productionTitle the production title
     */
    public void setProductionTitle(String productionTitle) {
        this.productionTitle.set(productionTitle);
    }

    /**
     * Production title property string property.
     *
     * @return the string property
     */
    public StringProperty productionTitleProperty() {
        return productionTitle;
    }

    /**
     * Gets channel name.
     *
     * @return the channel name
     */
    public String getChannelName() {
        return channelName.get();
    }

    /**
     * Sets channel name.
     *
     * @param channelName the channel name
     */
    public void setChannelName(String channelName) {
        this.channelName.set(channelName);
    }

    /**
     * Channel name property string property.
     *
     * @return the string property
     */
    public StringProperty channelNameProperty() {
        return channelName;
    }

    /**
     * Gets credits.
     *
     * @return the credits
     */
    public ObservableList<Credit> getCredits() {
        return credits.get();
    }

    /**
     * Sets credits.
     *
     * @param credits the credits
     */
    public void setCredits(ObservableList<Credit> credits) {
        this.credits.set(credits);
    }

    /**
     * Credits property list property.
     *
     * @return the list property
     */
    public ListProperty<Credit> creditsProperty() {
        return credits;
    }

    /**
     * Sets credits txt area.
     *
     * @param creditsTxtArea the credits txt area
     */
    public void setCreditsTxtArea(TextArea creditsTxtArea) {
        this.creditsTxtArea = creditsTxtArea;
    }

    /**
     * Sets credit.
     *
     * @param credit the credit
     */
    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    /**
     * Post credits.
     */
    public void postCredits() {
        var c = credit.getPerson().getName();
        var message = String.format("Posted credit for, c: '%s'", c);
        LOGGER.info(message);
        creditModel.postCredits(credit);
    }

    private void addCreditsToTextArea() {
        for (Credit cred : credits) {
            creditsTxtArea.appendText(cred.getPerson().getName() + "\t" + cred.getJob() + "\n");
        }
    }

    /**
     * Refresh values.
     */
    public void refreshValues() {
        // Set credits for chosen production
        Platform.runLater(this::addCreditsToTextArea);
    }

    /**
     * Gets production.
     *
     * @return the production
     */
    public Production getProduction() {
        return production;
    }

    /**
     * Sets production.
     *
     * @param production the production
     */
    public void setProduction(Production production) {
        this.production = production;
    }

    /**
     * Gets persons.
     *
     * @return the persons
     */
    public ObservableList<Person> getPersons() {
        return persons.get();
    }

    /**
     * Gets person.
     *
     * @param email the email
     * @return the person
     */
    public Person getPerson(String email) {
        for (Person p : personList) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Sets person.
     *
     * @param person the person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Post person.
     */
    public void postPerson() {
        var p = person.getName();
        var message = String.format("Posted person for, %s", p);
        LOGGER.info(message);
        personModel.postPerson(person);
    }
}
