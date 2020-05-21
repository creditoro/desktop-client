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

    public AddCreditViewModel(IPersonModel personModel, ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
        this.personModel = personModel;
        this.creditModel = creditModel;
    }

    public String getProductionTitle() {
        return productionTitle.get();
    }

    public void setProductionTitle(String productionTitle) {
        this.productionTitle.set(productionTitle);
    }

    public StringProperty productionTitleProperty() {
        return productionTitle;
    }

    public String getChannelName() {
        return channelName.get();
    }

    public void setChannelName(String channelName) {
        this.channelName.set(channelName);
    }

    public StringProperty channelNameProperty() {
        return channelName;
    }

    public ObservableList<Credit> getCredits() {
        return credits.get();
    }

    public void setCredits(ObservableList<Credit> credits) {
        this.credits.set(credits);
    }

    public ListProperty<Credit> creditsProperty() {
        return credits;
    }

    public void setCreditsTxtArea(TextArea creditsTxtArea) {
        this.creditsTxtArea = creditsTxtArea;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

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

    public void refreshValues() {
        // Set credits for chosen production
        Platform.runLater(this::addCreditsToTextArea);
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public ObservableList<Person> getPersons() {
        return persons.get();
    }

    public Person getPerson(String email) {
        for (Person p : personList) {
            if (p.getEmail().equals(email)){
                return p;
            }
        }
        return null;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
