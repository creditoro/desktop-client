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

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Add credit view model.
 */
public class AddCreditViewModel {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final ICreditModel creditModel;
    private final IPersonModel personModel;

    private final StringProperty queryParam = new SimpleStringProperty();
    private final StringProperty productionTitle = new SimpleStringProperty();
    private final StringProperty channelName = new SimpleStringProperty();
    private ObservableList<Credit> creditList = FXCollections.observableArrayList();
    private List<Credit> createdCredits = new ArrayList<>();

    private final ObservableList<Person> personList = FXCollections.observableArrayList();
    private final ListProperty<Person> persons = new SimpleListProperty<>(personList);

    @FXML
    private TextArea creditsTxtArea;

    private Credit credit;
    private Production production;
    private Person person;
    private Person[] people;

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
     * Get persons.
     */
    public void getPersons() {
        var q = queryParam.get();
        var message = String.format("Called search, q: '%s*", q);
        LOGGER.info(message);
        people = personModel.getPersons(q);
        setPersons();
    }

    public void setPersons() {
        persons.addAll(Arrays.asList(people));
    }

    /**
     * Refresh values.
     */
    public void refreshValues() {
        // Set credits for chosen production
        Platform.runLater(this::addCreditsToTextArea);
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
        return creditList;
    }

    /**
     * Sets credits.
     *
     * @param credits the credits
     */
    public void setCredits(ObservableList<Credit> credits) {
        this.creditList = credits;
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
        var c = credit.getJob();
        var message = String.format("Posted credit for job type: '%s'", c);
        LOGGER.info(message);
        creditModel.postCredits(credit);
    }

    private void addCreditsToTextArea() {
        creditsTxtArea.clear();
        for (Credit cred : creditList) {
            creditsTxtArea.appendText(cred.getPerson().getName() + "\t" + cred.getJob() + "\n");
        }
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
     * Gets person.
     *
     * @param email the email
     * @return the person
     */
    public Person getPerson(String email) {
        for (Person p : persons) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets person.
     *
     * @param name the name
     * @return the person
     */
    public Person getPersonByName(String name) {
        for (Person p : persons) {
            if (p.getName().equals(name)) {
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
        getPersons();
    }

    /**
     * Import credits.
     */
    public void importCredits() {
        JFileChooser fileChooser = new JFileChooser("user");
        fileChooser.showOpenDialog(null);

        File file = fileChooser.getSelectedFile();
        List<String> lines = new ArrayList<>();
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Something went wrong", e);
        }
        if (!lines.get(0).equalsIgnoreCase(productionTitle.getValue())) {
            LOGGER.info("Imported credits does not match current production");
            return;
        }
        List<Credit> cs = new ArrayList<>();
        String job = "";
        for (int i = 2; i < lines.size(); i++) {
            String l = convertToTitleCase(lines.get(i));
            if (lines.get(i - 1).isEmpty()) {
                job = convertToTitleCase(lines.get(i));
                continue;
            }
            if (l != null) {
                if (!l.isEmpty()) {
                    Person p = getPersonByName(l);
                    if (p == null) {
                        // create new person
                        p = new Person("phone", "email@" + l + ".dk", l);
                    }
                    if (!isCredit(job, p)) {
                        // create new credit
                        Credit c = new Credit(null, production, p, job);
                        cs.add(c);
                    }
                }
            }
        }
        creditList.addAll(cs);
        createdCredits.addAll(cs);
    }

    public List<Credit> getCreatedCredits() {
        return createdCredits;
    }

    /**
     * Is credit boolean.
     *
     * @param job    the job
     * @param person the person
     * @return the boolean
     */
    public boolean isCredit(String job, Person person) {
        for (Credit c : this.creditList) {
            if (c.getPerson().equals(person) && c.getJob().equalsIgnoreCase(job)) {
                return true;
            }
        }
        return false;
    }

    public static String convertToTitleCase (String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    public void finishCredits(List<Credit> deletedCredits){
        for (Credit cred : deletedCredits){
            if (!createdCredits.contains(cred)) {
                creditModel.deleteCredit(cred.getIdentifier());
            }
        }
        List<Person> personLst = new ArrayList<>();
        for (Credit cred : createdCredits) {
            Person p = cred.getPerson();
            var email = p.getEmail();
            if (getPerson(email) == (null)) {
                personLst.add(p);
            }
        }
        for (Person psn : personLst){
            personModel.postPerson(psn);
        }
        for (Credit cred : createdCredits){
            var personId = getPerson(cred.getPerson().getEmail()).getIdentifier();
            Credit temp = new Credit(production.getIdentifier(),personId,cred.getJob());
            creditModel.postCredits(temp);
        }
    }
}
