package dk.creditoro.client.view.add_credits;

import dk.creditoro.client.core.ViewModelFactory;
import dk.creditoro.client.model.credit.ICreditModel;
import dk.creditoro.client.model.crud.Credit;
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

public class AddCreditViewModel {
    private final StringProperty productionTitle = new SimpleStringProperty();
    private final ObservableList<Credit> creditList = FXCollections.observableArrayList();
    private final ListProperty<Credit> credits = new SimpleListProperty<>(creditList);

    @FXML
    private TextArea creditsTxtArea;

    public AddCreditViewModel(ICreditModel creditModel, IUserModel userModel, ViewModelFactory viewModelFactory) {
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

    private void addCreditsToTextArea() {
        for (Credit cred : credits) {
            creditsTxtArea.appendText(cred.getPerson().getName() + "\t" + cred.getJob() + "\n");
        }
    }

    public void refreshValues() {
        // Set credits for chosen production
        Platform.runLater(this::addCreditsToTextArea);
    }

}
